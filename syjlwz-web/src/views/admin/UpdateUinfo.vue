<template>
  <el-form
    ref="formDataRef"
    :rules="rules"
    :model="formData"
    label-width="80px"
    style="margin-top: 20px; margin-left: 20px"
  >
    <el-form-item label="用户名" prop="username">
      <el-input
        v-model="formData.username"
        readonly="true"
        
      ></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="userpwd">
      <el-input
        v-model="formData.userpwd"
        
        type="password"
      ></el-input>
    </el-form-item>
    <el-form-item label="姓名" prop="name">
      <el-input v-model="formData.name"  ></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="tel">
      <el-input v-model="formData.tel"  ></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="formData.email"  ></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" :loading="btnLoading" @click="save"
        >保存</el-button
      >
    </el-form-item>
  </el-form>
</template>

<script>
import request, { base } from "../../../utils/http";

export default {
  name: "UpdateUinfo",

  data() {
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
      btnLoading: false, //保存按钮加载状态
      formData: {},
      rules: {
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
  mounted() {
    var user = sessionStorage.getItem("userinfo");
    this.formData = JSON.parse(user);
 
  },
  methods: {
    save() {
      this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/uinfo/update?id=" + this.formData.id;
            this.btnLoading = true;
            request.post(url, this.formData).then((res) => {
              if (res.code == 200) {
                this.btnLoading = false;
                this.formVisible = false;
                this.$message({
                  message: "修改成功，请重新登录",
                  type: "success",
                  offset: 320,
                });
                 sessionStorage.removeItem("userinfo");
		         sessionStorage.removeItem("utype");
                 window.location.href="/uinfoLogin"
         
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
    },
  },
};
</script>

<style scoped>
</style>