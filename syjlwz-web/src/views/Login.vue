<template>
  <div class="loginbody">
    <div class="login">
      <div class="login-container">
        <el-form
          :mode="loginMode"
          ref="loginForm"
          label-position="left"
          label-width="0px"
          class="demo-ruleForm"
        >
          <h3 class="title">设计作品交流网站</h3>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.username"
              auto-complete="off"
              placeholder="用户名"
            ></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input
              type="password"
              v-model="loginMode.userpw"
              auto-complete="off"
              placeholder="密码"
            ></el-input>
          </el-form-item>
        
          <el-form-item style="width: 100%">
            <el-button
              type="primary"
              style="width: 100%"
              :loading="logining"
              @click="login"
              >登录</el-button
            >
          
          </el-form-item>
        </el-form>

       
      </div>
    </div>
  </div>
</template>

<script>
import request, { base } from "../../utils/http";

export default {
  data() {
     
    return {
      
    
       btnLoading: false, //保存按钮加载状态
        formVisible: false, //表单是否可见，默认不可见
      loginMode: {},
      loading: false,
    };
  },
  created() {
 
    
  },
  methods: {
  
    login() {
      let username = this.loginMode.username;
      let userpw = this.loginMode.userpw;

      if (!username) {
        this.$message({
          message: "请输入用户名",
          type: "error",
          offset: 320,
        });
        return false;
      }
      if (!userpw) {
        this.$message({
          message: "请输入密码",
          type: "error",
          offset: 320,
        });
        return false;
      }
        let url = base + "/ulogin/login";
        this.loading = true;
        request.post(url, this.loginMode).then((res) => {
          this.loading = false;
          if (res.code == 200) {
            console.log(JSON.stringify(res.resdata));
            sessionStorage.setItem("currentUser", JSON.stringify(res.resdata));
             sessionStorage.setItem("utype",'管理员');
            this.$router.push("/main");
          } else {
            this.$message({
              message: res.msg,
              type: "error",
            });
          }
        });
     

      //
    },
  },
};
</script>

<style   scoped>
.login-container {
  /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 360px;
  height: 380px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
.remember {
  margin: 0px 0px 35px 0px;
}

.loginbody {
  overflow: scroll;
  overflow-y: hidden;
  overflow-x: hidden;
}

.login {
  width: 100vw;
  padding: 0;
  margin: 0;
  height: 100vh;
  font-size: 16px;
  background-position: left top;
  background-color: #242645;
  color: #fff;
  font-family: "Source Sans Pro";
  position: relative;
  background-image: url("../assets/images/login-bg.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

.mylogin {
  width: 240px;
  height: 280px;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  padding: 50px 40px 40px 40px;
  box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
  opacity: 1;
  background: linear-gradient(
    230deg,
    rgba(53, 57, 74, 0) 0%,
    rgb(0, 0, 0) 100%
  );
}
</style>