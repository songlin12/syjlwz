<template>
    <el-form ref="formData"  :rules="rules" :model="formData" label-width="80px" style="margin-top: 20px;margin-left: 20px;">
        <el-form-item label="原密码" prop="opwd">
            <el-input   type="password"  v-model="formData.opwd"></el-input>
        </el-form-item>
        <el-form-item label="新密码"  prop="userpw">
            <el-input type="password"  v-model="formData.userpw"></el-input>
        </el-form-item>
        <el-form-item label="确认密码"  prop="confirmpw">
            <el-input type="password"  v-model="formData.confirmpw"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary"  :loading="btnLoading"  @click="save">保存</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    import request, {base} from "../../../utils/http";

    export default {
        name: "UpdatePwd",
        data(){
            return{
                btnLoading:false,//保存按钮加载状态
                formData:{},
                rules: {
                    opwd: [
                        { required: true, message: '请输入原密码', trigger: 'blur' }
                    ],
                    userpw: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                    confirmpw: [
                        { required: true, message: '请输入确认密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods:{
            save(){
                this.$refs.formData.validate((valid) => {
                    if (valid) {
                        if(this.formData.userpw!=this.formData.confirmpw){
                            this.$message({
                                message: '两次密码输入不一致！',
                                type: 'error',
                                offset:320
                            });
                            return;
                        }
                        let url = base + "/admin/updatePwd";
                        this.btnLoading = true;
                        var user = sessionStorage.getItem("currentUser");
                        user = JSON.parse(user)

                        var utype = sessionStorage.getItem("utype");
                        if("商家" == utype){
                             url = base + "/gsinfo/updatePwd";
                        }

                        this.formData.id = user.id;
                        request.post(url,this.formData).then(res => {
                            if(res.code==201){
                                this.btnLoading = false;
                                this.$message({
                                    message: '原密码错误！',
                                    type: 'error',
                                    offset:320
                                });

                            }else if(res.code==200){
                                this.btnLoading = false;
                                this.$message({
                                    message: '操作成功',
                                    type: 'success',
                                    offset:320
                                });

                            }else{
                                this.$message({
                                    message: '服务器错误',
                                    type: 'error',
                                    offset:320
                                });
                            }

                        })
                    }
                });

            }
        }
    }
</script>

<style scoped>

</style>