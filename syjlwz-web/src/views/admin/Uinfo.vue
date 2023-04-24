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
            v-model="filters.username"
            size="small"
            placeholder="用户名"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-input
            v-model="filters.name"
            size="small"
            placeholder="姓名"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="query">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="handleAdd"
            >新增</el-button
          >
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
        label="用户名"
        min-width="20%"
      ></el-table-column>

      <el-table-column
        prop="name"
        align="center"
        label="姓名"
        min-width="20%"
      ></el-table-column>
      <el-table-column
        prop="tel"
        align="center"
        label="电话"
        min-width="20%"
      ></el-table-column>
      <el-table-column
        prop="email"
        align="center"
        label="邮箱"
        min-width="20%"
      ></el-table-column>
      <el-table-column label="操作" align="center" width="300">
        <template #default="scope">
          <el-button
            type="primary"
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >

          <el-button
            type="warning"
            size="mini"
            @click="handleEmail(scope.$index, scope.row)"
            >邮件警告</el-button
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
      title="用户信息"
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
        <el-form-item label="用户名" prop="username">
          <el-input v-model="formData.username" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="userpwd">
          <el-input
            v-model="formData.userpwd"
            style="width: 50%"
            type="password"
          ></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData.name" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="formData.tel" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData.email" style="width: 50%"></el-input>
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
  </section>
</template>
<script>
import request, { base } from "../../../utils/http";
export default {
  name: "Uinfo",
  components: {},
  data() {
    var usernameExist = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else {
        let url = base + "/uinfo/usernameExist?username=" + value;
        request.post(url, this.formData).then((res) => {
          if (res.code == 200) {
            callback();
          } else {
            callback(new Error("用户名已存在"));
          }
        });
      }
    };
    var checkPhone = (rule, value, callback) => {
      const phoneReg = /^1[34578]\d{9}$/;
      if (!value) {
        return callback(new Error("电话不能为空"));
      }
      setTimeout(() => {
        // Number.isInteger是es6验证数字是否为整数的方法,实际输入的数字总是识别成字符串
        // 所以在前面加了一个+实现隐式转换
        if (!Number.isInteger(+value)) {
          callback(new Error("请输入数字值"));
        } else {
          if (phoneReg.test(value)) {
            callback();
          } else {
            callback(new Error("电话格式不正确"));
          }
        }
      }, 100);
    };
    var checkEmail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      if (!value) {
        return callback(new Error("邮箱不能为空"));
      }
      setTimeout(() => {
        if (mailReg.test(value)) {
          callback();
        } else {
          callback(new Error("请输入正确的邮箱格式"));
        }
      }, 100);
    };
    return {
      filters: {
        //列表查询参数
        username: "",
        userpwd: "",
        name: "",
      },
      page: {
        currentPage: 1, // 当前页
        pageSize: 10, // 每页显示条目个数
        totalCount: 0, // 总条目数
      },
      listLoading: false, //列表加载状态
      btnLoading: false, //保存按钮加载状态
      datalist: [], //表格数据
      formVisible: false, //表单是否可见，默认不可见
      formData: {}, //表单数据
      ureadonly: false,
      rules: {},
      addrules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { validator: usernameExist, trigger: "blur" },
        ],
        userpwd: [{ required: true, message: "请输入密码", trigger: "blur" }],
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        tel: [
          { required: true, message: "请输入电话", trigger: "blur" },
          { validator: checkPhone, trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          { validator: checkEmail, trigger: "blur" },
        ],
      },
      modifyrules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        userpwd: [{ required: true, message: "请输入密码", trigger: "blur" }],
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        tel: [
          { required: true, message: "请输入电话", trigger: "blur" },
          { validator: checkPhone, trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          { validator: checkEmail, trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getDatas();
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
            let url = base + "/uinfo/update?id=" + this.formData.id;
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
            let url = base + "/uinfo/add";
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
          let url = base + "/uinfo/del?id=" + row.id;
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

 handleEmail(index, row) {
      this.$confirm("确认要发邮件警告用户吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
   
          let url = base + "/uinfo/sendEmail?id=" + row.id;
          request.post(url).then((res) => {
          
            //NProgress.done();
            this.$message({
              message: "操作成功",
              type: "success",
              offset: 320,
            });
      
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
        username: this.filters.username,
        userpwd: this.filters.userpwd,
        name: this.filters.name,
      };
      this.listLoading = true;
      let url = base + "/uinfo/list?currentPage=" + this.page.currentPage;
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
  },
};
</script>
