<template>
  <div>
    <div class="passport-wrapper">
      <div id="container">
        <div class="passport-sign">
          <div class="main-form">
            <h2>用户登录</h2>
            <p></p>
            <div class="passport-form passport-form-sign">
            <div class="form-item">
              <div class="form-cont">
                <input
                  type="text"
                  name="username"
                  id="username"
                  v-model="formData.username"
                  class="passport-txt xl w-full"
                  placeholder="请输入用户名"
                />
              </div>
            </div>
            <div class="form-item">
              <div class="form-cont">
                <input
                  type="password"
                  name="userpwd"
                  id="userpwd"
                  v-model="formData.userpwd"
                  class="passport-txt xl w-full"
                  placeholder="请输入密码"
                />
              </div>
            </div>
            

            <div class="form-item">
              <div class="form-cont">
                <button
                  type="button"
                  name="register"
                  id="register"
                  @click="register"
                  class="passport-btn passport-btn-def xl w-full"
                  tabindex="5"
                >
                  登录
                </button>
              </div>
            </div>
            </div>

            <!-- <el-col :span="12" style="text-align: right">
                <el-link @click="router.push('/forget')">忘记密码？</el-link>
            </el-col> -->

            <div style="margin-bottom: 0px;float: right;">
              <a href="/forget" tabindex="6">忘记密码</a>
            </div>
          
            <!-- form end -->
            <div class="passport-goto mg-b100" style="margin-bottom: 0px">
              没有帐号 <a href="/register" tabindex="6">去注册</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
 
<script>
import request, { base } from "../../../utils/http";
import store from "../../store";
export default {
  components: {},
  data() {
    return {
      //banPng: require("@/assets/images/09105428_3945.jpg"),
      //logopng: require("@/assets/images/logopng.png"),
      formData: {},
    };
  },

  created() {},
  methods: {
    register() {
      let username = this.formData.username;
      let userpwd = this.formData.userpwd;
     
      if (!username) {
        this.$message({
          message: "请输入用户名",
          type: "error",
          offset: 320,
        });
        return false;
      }

      if (!userpwd) {
        this.$message({
          message: "请输入密码",
          type: "error",
          offset: 320,
        });
        return false;
      }
      
      let url = base + "/uinfo/login";

      request.post(url, this.formData).then((res) => {
        if (res.code == 200) {
           sessionStorage.setItem("userinfo", JSON.stringify(res.resdata));
           sessionStorage.setItem("utype", '用户');
 
           window.location.href="/index";
          //this.$router.push("/index");
        } else {
          this.$message({
            message: res.msg,
            type: "error",
          });
        }
      });
    },
  },
};
</script>


<style>
@import "../../assets/css/regiset.css";
@import "../../assets/css/passport.min.css";
</style>
 
