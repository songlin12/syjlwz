<template>
    <div>
      <div class="passport-wrapper">
        <div id="container">
          <div class="passport-sign">
            <div class="main-form">
              <h2>重置密码</h2>
              <p></p>
              <div class="passport-form passport-form-sign">
              <div class="form-item">
                <div class="form-cont">
                  <input
                    type="text"
                    name="email"
                    id="email"
                    v-model="formData.email"
                    class="passport-txt xl w-full"
                    placeholder="请输入邮箱"
                  />
                </div>
              </div>
              <div class="form-item">
                <div class="form-cont" style="float:left">
                  <input
                    type="password"
                    name="userpwd"
                    id="userpwd"
                    v-model="formData.pwd"
                    class="passport-txt xl w-full"
                    placeholder="请输入验证码"
                  />
                </div>
                <div style="float:right">
                    <button
                    type="button"
                    name="sendPwd"
                    id="sendPwd"
                    @click="sendPwd"
                    style="width: 180px;"
                    class="passport-btn passport-btn-def xl w-full"
                    tabindex="6"
                  >
                    获取验证码
                  </button>
                </div>
              </div>
              
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
        
        formData: {},
      };
    },
  
    created() {},
    methods: {
      forget() {
        let email = this.formData.email;
        let pwd = this.formData.pwd;
       
        if (!email) {
          this.$message({
            message: "请输入邮箱",
            type: "error",
            offset: 320,
          });
          return false;
        }
  
        if (!pwd) {
          this.$message({
            message: "请输入验证码",
            type: "error",
            offset: 320,
          });
          return false;
        }
        
        let url = base + "/uinfo/login";
  
        // request.post(url, this.formData).then((res) => {
        //   if (res.code == 200) {
        //      sessionStorage.setItem("userinfo", JSON.stringify(res.resdata));
        //      sessionStorage.setItem("utype", '用户');
   
        //      window.location.href="/index";
        //   } else {
        //     this.$message({
        //       message: res.msg,
        //       type: "error",
        //     });
        //   }
        // });
      },
    },
  };
  </script>
  
  
  <style>
  @import "../../assets/css/regiset.css";
  @import "../../assets/css/passport.min.css";
  </style>