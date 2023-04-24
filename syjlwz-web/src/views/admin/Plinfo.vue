<template>
  <section style="margin: 10px">
    <!--工具条-->
    <el-col
      :span="24"
      class="toolbar"
      style="padding-bottom: 0px; margin-left: 10px"
    >
      <el-form :inline="true" :model="filters">
         <el-form-item>
          <el-input
            v-model="filters.zpname"
            size="small"
            placeholder="作品名称"
          ></el-input>
        </el-form-item>
         
        <el-form-item>
          <el-button type="primary" size="small" @click="query">查询</el-button>
        </el-form-item>
      
      </el-form>
    </el-col>
    <!--列表-->
    <el-table
      :data="datalist"
      border
      stripe
      v-loading="listLoading"
      highlight-current-row
      max-height="600"
      size="small"
      style="width: 100%"
    >
 



          <el-table-column
        prop="fujian"
        align="center"
        label="作品"
        min-width="20%"
      >
      
          <template  #default="scope">       
               <div style="text-align:center" align="center">
                   <div>
                    <img :src="'http://localhost:8088/syjlwz/'+scope.row.zpinfoVO.fujian"  min-width="200" height="100" />
                   </div>
                    <div v-text="scope.row.zpinfoVO.name"></div>
 
               </div>     
                    
          </template>      
      </el-table-column>





      <el-table-column
        prop="username"
        align="center"
        label="用户"
        min-width="20%"
      >
        <template #default="scope">{{ scope.row.uinfoVO.username }}</template>
      </el-table-column>

      <el-table-column
        prop="plnr"
        align="center"
        label="评论内容"
        min-width="20%"
      ></el-table-column>
      <el-table-column
        prop="plsj"
        align="center"
        label="评论时间"
        min-width="20%"
      ></el-table-column>
       <el-table-column
        prop="plhf"
        align="center"
        label="评论回复"
        min-width="20%"
      ></el-table-column>

      <el-table-column label="操作" align="center" width="300">
        <template #default="scope">
          
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="page.currentPage"
      :page-size="page.pageSize"
      background
      layout="total, prev, pager, next, jumper"
      :total="page.totalCount"
      style="float: right; margin: 10px 20px 0 0"
    >
    </el-pagination>
    <!--表单-->
    <el-dialog
      title="评论信息"
      v-model="formVisible"
      width="40%"
      :close-on-click-modal="false"
    >
      <el-form
        :model="formData"
        ref="formDataRef"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="作品" prop="zpid">
          <el-select
            v-model="formData.zpid"
            placeholder="请选择"
            style="width: 50%"
          >
            <el-option label="请选择" value="0"> </el-option>
            <el-option
              v-for="item in zpinfoList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户" prop="uid">
          <el-select
            v-model="formData.uid"
            placeholder="请选择"
            style="width: 50%"
          >
            <el-option label="请选择" value="0"> </el-option>
            <el-option
              v-for="item in uinfoList"
              :key="item.id"
              :label="item.username"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评论内容" prop="plnr">
          <el-input
            v-model="formData.plnr"
            type="textarea"
            rows="10"
            style="width: 80%"
          ></el-input>
        </el-form-item>
        <el-date-picker
          :editable="false"
          size="small"
          v-model="filters.plsj"
          type="date"
          placeholder="评论时间"
        >
        </el-date-picker>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="formVisible = false">取 消</el-button>
          <el-button type="primary" :loading="btnLoading" @click="save"
            >确 定</el-button
          >
        </span>
      </template>
    </el-dialog>
  </section>
</template>
<script>
import request, { base } from "../../../utils/http";
export default {
  name: "Plinfo",
  components: {},
  data() {
    return {
      filters: {
        //列表查询参数
        zpname: ""
        
      },
      page: {
        currentPage: 1, // 当前页
        pageSize: 10, // 每页显示条目个数
        totalCount: 0, // 总条目数
      },
      zpinfoList: "",
      uinfoList: "",
      listLoading: false, //列表加载状态
      btnLoading: false, //保存按钮加载状态
      datalist: [], //表格数据
      formVisible: false, //表单是否可见，默认不可见
      formData: {}, //表单数据
      ureadonly: false,
      rules: {},
      addrules: {
        plnr: [{ required: true, message: "请输入评论内容", trigger: "blur" }],
      },
      modifyrules: {
        plnr: [{ required: true, message: "请输入评论内容", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getDatas();
    this.getZpinfoList();
    this.getUinfoList();
  },
  methods: {
    handleAdd() {
      //弹出新增窗口
      this.formVisible = true;
      this.ureadonly = false;
      this.rules = this.addrules;
      this.$nextTick(() => {
        this.$refs["formDataRef"].resetFields();
      });
    },
    handleEdit(index, row) {
      //打开修改界面
      this.formVisible = true;
      this.ureadonly = true;
      this.rules = this.modifyrules;
      this.formData = JSON.parse(JSON.stringify(row));
    },
    save() {
      if (this.formData.id != null) {
        //如果有ID就修改数据
        this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/plinfo/update?id=" + this.formData.id;
            this.btnLoading = true;
            request.post(url, this.formData).then((res) => {
              if (res.code == 200) {
                this.btnLoading = false;
                this.formVisible = false;
                this.$message({
                  message: "操作成功",
                  type: "success",
                  offset: 320,
                });
                this.getDatas();
                this.$refs["formDataRef"].resetFields();
                this.formData = {};
              } else {
                this.$message({
                  message: "服务器错误",
                  type: "error",
                  offset: 320,
                });
              }
            });
          }
        });
      } else {
        //如果没有ID就新增数据
        this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/plinfo/add";
            //var user = sessionStorage.getItem("currentUser");
            //user = JSON.parse(user);
            this.btnLoading = true;
            request.post(url, this.formData).then((res) => {
              console.log(res);
              if (res.code == 200) {
                this.$message({
                  message: "操作成功",
                  type: "success",
                  offset: 320,
                });
                this.btnLoading = false;
                this.formVisible = false;
                this.getDatas();
                this.$refs["formDataRef"].resetFields();
                this.formData = {};
              } else {
                this.$message({
                  message: "服务器错误",
                  type: "error",
                  offset: 320,
                });
              }
            });
          }
        });
      }
    },
    handleDelete(index, row) {
      this.$confirm("确认删除该记录吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.listLoading = true;
          let url = base + "/plinfo/del?id=" + row.id;
          request.post(url).then((res) => {
            this.listLoading = false;
            //NProgress.done();
            this.$message({
              message: "删除成功",
              type: "success",
              offset: 320,
            });
            this.getDatas();
          });
        })
        .catch(() => {});
    },
    handleCurrentChange(val) {
      this.page.currentPage = val;
      this.getDatas();
    },
    query() {
      //查询
      this.getDatas();
    },
    //获取列表数据
    getDatas() {
      //var user = sessionStorage.getItem("currentUser");
      //user = JSON.parse(user);
      let para = {
        zpname: this.filters.zpname
     
      };
      this.listLoading = true;
      let url = base + "/plinfo/list?currentPage=" + this.page.currentPage;
      console.log(url);
      request.post(url, para).then((res) => {
        console.log(res);
        if (res.resdata.length > 0) {
          this.isPage = true;
        } else {
          this.isPage = false;
        }
        this.page.totalCount = res.count;
        this.datalist = res.resdata;
        this.listLoading = false;
      });
    },
    getZpinfoList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/plinfo/listZpinfoAll";
      request.post(url, para).then((res) => {
        this.zpinfoList = res.resdata;
      });
    },
    getUinfoList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/plinfo/listUinfoAll";
      request.post(url, para).then((res) => {
        this.uinfoList = res.resdata;
      });
    },
  },
};
</script>
