<template>
  <div>
    <div class="passport-wrapper">
      <div id="container" style="margin-top: -50px">
        <div class="passport-sign">
          <div class="main-form">
            <h2>新用户注册</h2>
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
                <input
                  type="password"
                  name="userpwd1"
                  id="userpwd1"
                  v-model="formData.userpwd1"
                  class="passport-txt xl w-full"
                  placeholder="请输入密码"
                />
              </div>
            </div>

            <div class="form-item">
              <div class="form-cont">
                <input
                  type="text"
                  name="name"
                  id="name"
                  v-model="formData.name"
                  class="passport-txt xl w-full"
                  placeholder="请输入姓名"
                />
              </div>
            </div>

            <div class="form-item">
              <div class="form-cont">
                <input
                  type="text"
                  name="email"
                  id="email"
                  v-model="formData.email"
                  class="passport-txt xl w-full"
                  placeholder="输入邮箱"
                />
              </div>
            </div>

            <div class="form-item">
              <div class="form-cont">
                <input
                  type="text"
                  name="tel"
                  id="tel"
                  class="passport-txt xl w-full"
                  v-model="formData.tel"
                  placeholder="输入联系方式"
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
                  注册
                </button>
              </div>
            </div>
            </div>
            <!-- form end -->
            <div class="passport-goto mg-b100" style="margin-bottom: 0px">
              已有帐号 <a href="/uinfoLogin" tabindex="6">立即登录</a>
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
      let userpwd1 = this.formData.userpwd1;
      let tel = this.formData.tel;
      let name = this.formData.name;
      let email = this.formData.email;
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
      if (!userpwd1) {
        this.$message({
          message: "请输入确认密码",
          type: "error",
          offset: 320,
        });
        return false;
      }

      if (userpwd != userpwd1) {
        this.$message({
          message: "两次密码输入不一致",
          type: "error",
          offset: 320,
        });
        return false;
      }
      if (!name) {
        this.$message({
          message: "请输入姓名",
          type: "error",
          offset: 320,
        });
        return false;
      }
      if (!email) {
        this.$message({
          message: "请输入邮箱",
          type: "error",
          offset: 320,
        });
        return false;
      }

      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      if (!mailReg.test(email)) {
        this.$message({
          message: "请输入正确的邮箱",
          type: "error",
          offset: 320,
        });
        return false;
      }

      if (!tel) {
        this.$message({
          message: "请输入电话",
          type: "error",
          offset: 320,
        });
        return false;
      }

      let url = base + "/uinfo/uregister";

      request.post(url, this.formData).then((res) => {
        if (res.code == 200) {
          this.$router.push("/uinfoLogin");
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
 
