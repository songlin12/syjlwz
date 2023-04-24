<template>
  <div id="header">
    <div id="menu">
      <div id="logo">
        <div id="system_title">设计作品交流网站</div>
      </div>
      <div id="rinfo">
        


        当前用户：
        <span>{{ userName }} </span>
        <span id="exit">
          <a @click="exit">退出</a>
        </span>

        <span v-if="utype=='用户'" style="margin-left:20px;" >
          <a @click="toHome">返回首页</a>
        </span>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Heeadr",
  data() {
    return {
      userName: "",
      utype: "",
    };
  },
  mounted() {
    var utype = sessionStorage.getItem("utype");
    this.utype = utype || "";
    if (utype == "管理员") {
      var user = sessionStorage.getItem("currentUser");
      user = JSON.parse(user);
      if (user) {
        this.userName = user.name || "";
      }
    }
     if (utype == "用户") {
      var user = sessionStorage.getItem("userinfo");
      user = JSON.parse(user);
      if (user) {
        this.userName = user.username || "";
      }
    }
  },
  methods: {
      toHome:function(){
          window.location.href="/index";
      },
    exit: function () {
      var _this = this;
      this.$confirm("确认退出吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          sessionStorage.removeItem("currentUser");
           sessionStorage.removeItem("userinfo");
          sessionStorage.removeItem("utype");
          _this.$router.push("/index");
        })
        .catch(() => {});
    },
  },
};
</script>

<style scoped>
</style>