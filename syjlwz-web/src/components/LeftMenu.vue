<template>
    <el-menu
           style="width:220px; min-height : calc(100vh - 90px)"
           router
             default-active="admin"
            class="el-menu-vertical-demo" >
        <el-submenu index="1" style="width:220px;" v-if="utype=='管理员'" >
            <template #title>
                <i class="el-icon-location"></i>
                <span>系统管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="admin">管理员管理</el-menu-item>
                <el-menu-item index="updatePwd">修改密码</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
 

        <!-- <el-menu-item index="zpqy"  v-if="utype=='管理员'"  >
            <i class="el-icon-setting"></i>
            <template #title>区域管理</template>
        </el-menu-item> -->
        <el-menu-item index="zptype"  v-if="utype=='管理员'">
            <i class="el-icon-setting"></i>
            <template #title>类型管理</template>
        </el-menu-item>
        
         <!-- <el-menu-item index="zpxs"  v-if="utype=='管理员'" >
            <i class="el-icon-setting"></i>
            <template #title>像素管理</template>
        </el-menu-item> -->
 

          <el-menu-item index="uinfo" v-if="utype=='管理员'" >
            <i class="el-icon-setting"></i>
            <template #title>用户管理</template>
        </el-menu-item>
        <el-menu-item index="zpinfo" v-if="utype=='管理员'" >
            <i class="el-icon-setting"></i>
            <template #title>作品管理</template>
        </el-menu-item>

         <el-menu-item index="plinfo" v-if="utype=='管理员'">
            <i class="el-icon-setting"></i>
            <template #title>评论管理</template>
        </el-menu-item>


    
        
         <el-menu-item index="updateUinfo" v-if="utype!='管理员'" >
            <i class="el-icon-setting"></i>
            <template #title>个人资料</template>
        </el-menu-item>

        
        <el-menu-item index="uinfoZpinfo" v-if="utype!='管理员'" >
            <i class="el-icon-setting"></i>
            <template #title>作品管理</template>
        </el-menu-item>

         <el-menu-item index="uinfoPlinfo" v-if="utype!='管理员'">
            <i class="el-icon-setting"></i>
            <template #title>评论管理</template>
        </el-menu-item>

          

    </el-menu>
</template>

<script>
export default {
  name: "LeftMenu",
  data() {
    return {
      utype: '' 
    };
    
  },
  created() {
     let utype = sessionStorage.getItem('utype');
     this.utype = utype;
     
  },
};
</script>

<style scoped>

</style>