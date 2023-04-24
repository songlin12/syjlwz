<template>
  <section style="margin: 10px">
    <!--工具条-->
    <el-col
      :span="24"
      class="toolbar"
      style="padding-bottom: 0px; margin-left: 10px"
    >
      <el-form :inline="true" :model="filters">
        <el-form-item label="用户" prop="uid">
          <el-input
            v-model="filters.uname"
            size="small"
            placeholder="用户名"
          ></el-input>
        </el-form-item>
        <!-- <el-form-item label="区域" prop="qyid">
          <el-select
            size="small"
            v-model="filters.qyid"
            placeholder="请选择"
            style="width: 50%"
          >
            <el-option label="请选择" value="0"> </el-option>
            <el-option
              v-for="item in zpqyList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item label="类型" prop="ztid">
          <el-select
            size="small"
            v-model="filters.ztid"
            placeholder="请选择"
            style="width: 50%"
          >
            <el-option label="请选择" value="0"> </el-option>
            <el-option
              v-for="item in zptypeList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="像素" prop="xsid">
          <el-select
            size="small"
            v-model="filters.xsid"
            placeholder="请选择"
            style="width: 50%"
          >
            <el-option label="请选择" value="0"> </el-option>
            <el-option
              v-for="item in zpxsList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item> -->
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
        prop="username"
        align="center"
        label="用户"
        min-width="20%"
      >
        <template #default="scope">{{ scope.row.uinfoVO.username }}</template>
      </el-table-column>
 
      <!-- <el-table-column prop="name" align="center" label="区域" min-width="20%">
        <template #default="scope">{{ scope.row.zpqyVO.name }}</template>
      </el-table-column> -->
      <el-table-column prop="name" align="center" label="类型" min-width="20%">
        <template #default="scope">{{ scope.row.zptypeVO.name }}</template>
      </el-table-column>
      <!-- <el-table-column prop="name" align="center" label="像素" min-width="20%">
        <template #default="scope">{{ scope.row.zpxsVO.name }}</template>
      </el-table-column> -->
 
      
            <el-table-column
        prop="fujian"
        align="center"
        label="作品"
        min-width="20%"
      >
      
          <template  #default="scope">       
               <div style="text-align:center" align="center">
                   <div>
                    <img :src="'http://localhost:8088/syjlwz/'+scope.row.fujian"  min-width="200" height="100" />
                   </div>
                    <div v-text="scope.row.title"></div>

                      <div v-text="scope.row.name "></div>
               </div>     
                    
          </template>      
      </el-table-column>


      <el-table-column label="操作" align="center" width="300">
        <template #default="scope">

          <el-button
            size="mini"
            type="danger"
            @click="changeState(scope.$index,scope.row,'审核不通过')"  :disabled="scope.row.state !=='待审核'"
            >审核不通过</el-button
          >

          <el-button
            size="mini"
            type="success"
            @click="changeState(scope.$index,scope.row,'审核通过')"  :disabled="scope.row.state !=='待审核'"
            >审核通过</el-button
          >
         
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
      title="作品信息"
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
        <el-form-item label="作品名称" prop="name">
          <el-input v-model="formData.name" style="width: 50%"></el-input>
        </el-form-item>
        <!-- <el-form-item label="区域" prop="qyid"  >
          <el-select
            v-model="formData.qyid"
            placeholder="请选择"
            style="width: 50%;"
          >
            <el-option label="请选择" value="0"> </el-option>
            <el-option
              v-for="item in zpqyList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item label="类型" prop="ztid" >
          <el-select
            v-model="formData.ztid"
            placeholder="请选择"
            style="width: 50%"
          >
            <el-option label="请选择" value="0"> </el-option>
            <el-option
              v-for="item in zptypeList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="像素" prop="xsid">
          <el-select
            v-model="formData.xsid"
            placeholder="请选择"
            style="width: 50%"
          >
            <el-option label="请选择" value="0"> </el-option>
            <el-option
              v-for="item in zpxsList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item label="附件" prop="fujian">
          <el-input
            v-model="formData.fujian"
            readonly="true"
            style="width: 50%"
          ></el-input>
          <el-button
            type="primary"
            size="small"
            style="margin-left: 15px"
            @click="showUpload"
            >上传</el-button
          >
        </el-form-item>
      
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
    <el-dialog
      v-model="uploadVisible"
      title="附件上传"
      custom-class="el-dialog-widthSmall"
      @close="closeDialog"
    >
      <div>
        <b>请选择文件（jpg,jpeg,png格式）进行上传！&emsp;</b>
      </div>
      <el-upload
        action="http://localhost:8088/syjlwz/api/common/uploadFile"
        style="
          margin: auto;
          margin-top: 10px;
          border: 1px solid #dcdfe6;
          border-radius: 4px;
        "
        drag
        :limit="1"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        :on-exceed="handleExceed"
        :auto-upload="false"
        name="file"
        :on-change="fileListChange"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将数据文件拖到此处，或<em>点击上传</em>
        </div>
        <div class="el-upload__tip">
          <div
            style="display: inline; color: #d70000; font-size: 14px"
            class="uploadFileWarning"
            id="uploadFileWarning"
          ></div>
        </div>
      </el-upload>
      <span class="dialog-footer">
        <el-button @click="hideUpload">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">提 交</el-button>
      </span>
    </el-dialog>
  </section>
</template>
<script>
import request, { base } from "../../../utils/http";
export default {
  name: "Zpinfo",
  components: {},
  data() {
    return {
      filters: {
        //列表查询参数
        uid: "",
        qyid: "",
        ztid: "",
        xsid: "",
        uname:''
      },
      page: {
        currentPage: 1, // 当前页
        pageSize: 10, // 每页显示条目个数
        totalCount: 0, // 总条目数
      },
      form:{},
      uploadVisible: false,
      uinfoList: "",
      zpqyList: "",
      zptypeList: "",
      zpxsList: "",
      listLoading: false, //列表加载状态
      btnLoading: false, //保存按钮加载状态
      datalist: [], //表格数据
      formVisible: false, //表单是否可见，默认不可见
      formData: {}, //表单数据
      ureadonly: false,
      rules: {},
      addrules: {
        name: [{ required: true, message: "请输入作品名称", trigger: "blur" }],
        fujian: [{ required: true, message: "请输入作品", trigger: "blur" }],
      },
      modifyrules: {
        name: [{ required: true, message: "请输入作品名称", trigger: "blur" }],
        fujian: [{ required: true, message: "请输入作品", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getDatas();
    this.getUinfoList();
    this.getZpqyList();
    this.getZptypeList();
    this.getZpxsList();
  },
  methods: {
    //审核判断
    changeState(row,state){
      this.formVisible = true;
      this.ureadonly = true;
      this.form = JSON.parse(JSON.stringify(row))
      this.form.state = state;
      this.saveState();
    },
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
    saveState(){
      this.request.post("/zpin",this.form).then(res =>{
        if(res.code == '200'){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.error("保存失败")
        }
      })
    },
    save() {
      if (this.formData.id != null) {
        //如果有ID就修改数据
        this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/zpinfo/update?id=" + this.formData.id;
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
            let url = base + "/zpinfo/add";
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
          let url = base + "/zpinfo/del?id=" + row.id;
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
    showUpload() {
      this.uploadVisible = true;
    },
    hideUpload() {
      this.uploadVisible = false;
    },
    //上传
    handleRemove(file, fileList) {
      this.fileList = fileList;
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message({
        duration: 1000,
        message: "只能上传一个文件",
        type: "error",
        offset: 320,
      });
    },
    // 判断上传文件后缀
    fileListChange(file, fileList) {
      let extendFileName = "jpg,jpeg,png";
      let extendFileNames = extendFileName.split(",");
      let regExpRules = [];
      for (let i = 0; i < extendFileNames.length; i++) {
        regExpRules.push(
          new RegExp("(.*).(" + extendFileNames[i] + ")$", "gim")
        );
      }
      let fileNames = [];
      let files = [];
      let that = this;
      fileList.forEach(function (key, val) {
        let ret = false;
        for (let i = 0; i < regExpRules.length; i++) {
          ret = ret || regExpRules[i].test(key["name"]);
        }
        if (!ret) {
          console.log(key["name"] + ":" + ret);
          that.$message({
            duration: 1000,
            message: "上传的文件后缀必须为" + extendFileName + "格式！",
            type: "error",
            offset: 320,
          });
          return false;
        }
        if (fileNames.indexOf(key["name"]) != -1) {
          that.$message({
            duration: 1000,
            message: "上传的文件重复！",
            type: "error",
            offset: 320,
          });
          return false;
        }
        //只能上传一个文件，用最后上传的覆盖
        if (!that.multiFiles) {
          files = [];
          fileNames = [];
        }
        files.push(key);
        fileNames.push(key["name"]);
        if (fileNames !== "") {
          // $('#uploadMad .el-upload-dragger').css('border-color', '#409eff');
        }
        //$(".uploadFileWarning").text("");
      });
      this.files = fileNames;
      this.fileList = files;
    },
    /**
     * 确认按钮
     */
    handleConfirm() {
      let filePath = this.fileList;
      if (filePath.length === 0) {
        this.$message({
          duration: 1000,
          message: "请选择文件！",
          type: "error",
          offset: 320,
        });
        return false;
      }
      let formData = new FormData();
      this.fileList.forEach((file) => {
        formData.append("file", file.raw, file.raw.name);
      });
      let url = base + "/common/uploadFile";
      console.log("url=" + url);
      request.post(url, formData).then((res) => {
        console.log(res);
        let furl = res.resdata.filePath;
        this.formData.fujian = furl;
        this.hideUpload();
        console.log(res);
      });
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
        qyid: this.filters.qyid,
        ztid: this.filters.ztid,
        xsid: this.filters.xsid,
        uname: this.filters.uname,
      };
      this.listLoading = true;
      let url = base + "/zpinfo/list?currentPage=" + this.page.currentPage;
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
    getUinfoList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/zpinfo/listUinfoAll";
      request.post(url, para).then((res) => {
        this.uinfoList = res.resdata;
      });
    },
    getZpqyList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/zpinfo/listZpqyAll";
      request.post(url, para).then((res) => {
        this.zpqyList = res.resdata;
      });
    },
    getZptypeList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/zpinfo/listZptypeAll";
      request.post(url, para).then((res) => {
        this.zptypeList = res.resdata;
      });
    },
    getZpxsList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/zpinfo/listZpxsAll";
      request.post(url, para).then((res) => {
        this.zpxsList = res.resdata;
      });
    },
  },
};
</script>
