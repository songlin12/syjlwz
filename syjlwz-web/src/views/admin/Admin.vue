<template>
    <section style="margin: 10px;">
      <!--工具条-->
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;margin-left: 10px">
          <el-form :inline="true"  :model="filters" >
              <el-form-item>
                  <el-input  v-model="filters.username"  size="small" placeholder="用户名"></el-input>
              </el-form-item>
              <el-form-item>
                  <el-input  v-model="filters.name"  size="small" placeholder="姓名"></el-input>
              </el-form-item>
          
              <el-form-item>
                  <el-button type="primary" size="small"  @click="query" >查询</el-button>
              </el-form-item>
              <el-form-item>
                  <el-button type="primary" size="small"  @click="handleAdd">新增</el-button>
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
              size ="small"
              style="width: 100%;"
      >
          <el-table-column prop="username" align="center" label="用户名" min-width="20%"></el-table-column>
             <el-table-column prop="name" align="center" label="姓名" min-width="20%"></el-table-column>
                <el-table-column prop="tel" align="center" label="电话" min-width="20%"></el-table-column>
       
          <el-table-column label="操作" align="center" width="300" >


              <template #default="scope">
                  <el-button  v-show="scope.row.username!='admin'"
                          type="primary"
                          size="mini"
                          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button v-show="scope.row.username!='admin'"
                          size="mini"
                          type="danger"
                          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
                style="float:right;margin:10px 20px 0 0;"
             >
        </el-pagination>

        <!--表单-->
        <el-dialog title="管理员信息" v-model="formVisible"  width="40%"  :close-on-click-modal="false">
            <el-form :model="formData" ref="formDataRef" :rules="rules"  label-width="100px">
                <el-form-item label="用户名"  prop="username">
                    <el-input v-model="formData.username" :readonly="ureadonly" style="width:50%"></el-input>
                </el-form-item>

                <el-form-item label="密码"   prop="userpw">
                    <el-input v-model="formData.userpw" type="password" style="width:50%"></el-input>
                </el-form-item>

                 <el-form-item label="姓名"  prop="name">
                    <el-input v-model="formData.name"   style="width:50%"></el-input>
                 </el-form-item>

                 <el-form-item label="电话"  prop="tel">
                    <el-input v-model="formData.tel"  style="width:50%"></el-input>
                </el-form-item>

    
 

            </el-form>

            <template #footer>
                <span class="dialog-footer">
                  <el-button @click="formVisible = false">取 消</el-button>
                  <el-button type="primary" :loading="btnLoading" @click="save">确 定</el-button>
                </span>
            </template>
        </el-dialog>



  </section>
</template>

<script>


import request, {base} from "../../../utils/http";

export default {
  name: 'Admin',
  components: {

  },
    data(){
      var checkUsername = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入用户名'));
            }else{
                let url = base + "/admin/checkUsername?username=" + value;
                request.post(url,this.formData).then(res => {
                    if(res.code==200){
                        callback();
                    }else{
                        callback(new Error('用户名已存在'));
                    }

                })
            }

        };
      return {
          filters: {    //列表查询参数
              username: '',
              name:'',
              utype:''
          },
          page: {
              currentPage: 1, // 当前页
              pageSize: 10, // 每页显示条目个数
              totalCount: 0 // 总条目数
          },
          listLoading:false,//列表加载状态
          btnLoading:false,//保存按钮加载状态
          datalist: [], //表格数据
          formVisible:false, //表单是否可见，默认不可见
          formData:{}, //表单数据
          ureadonly:false,
          rules:{},
          addrules: {
              username: [
                  { required: true, message: '请输入用户名', trigger: 'blur' },
                  { validator: checkUsername, trigger: 'blur' }
              ],
              userpw: [
                  { required: true, message: '请输入密码', trigger: 'blur' }
              ],
              name: [
                  { required: true, message: '请输入姓名', trigger: 'blur' }
              ],
                tel: [
                  { required: true, message: '请输入电话', trigger: 'blur' }
              ],
 
          },
          modifyrules: {
              userpw: [
                  { required: true, message: '请输入密码', trigger: 'blur' }
              ],
               name: [
                  { required: true, message: '请输入姓名', trigger: 'blur' }
              ],
                tel: [
                  { required: true, message: '请输入电话', trigger: 'blur' }
              ],

              
          }
      }
    },
    created() {
       this.getDatas();

    },
    methods: {
        handleAdd(){  //弹出新增窗口
            this.formVisible = true;
            this.ureadonly = false;
            this.rules = this.addrules;
            this.$nextTick(()=>{
                this.$refs['formDataRef'].resetFields();
            })

        },
        handleEdit(index,row){ //打开修改界面
            this.formVisible = true;
            this.ureadonly = true;
            this.rules = this.modifyrules;
            this.formData = JSON.parse(JSON.stringify(row));
        },
        save(){
            if(this.formData.id!=null){ //如果有ID就修改数据
                this.$refs['formDataRef'].validate((valid) => {
                        if (valid) {
                            let url = base + "/admin/update?id=" + this.formData.id;
                            this.btnLoading = true;
                            request.post(url,this.formData).then(res => {
                                if(res.code==200){
                                    this.btnLoading = false;
                                    this.formVisible = false;
                                    this.$message({
                                        message: '操作成功',
                                        type: 'success',
                                        offset:320
                                    });
                                    this.getDatas();
                                    this.$refs['formDataRef'].resetFields();
                                    this.formData={};
                                }else{
                                    this.$message({
                                        message: '服务器错误',
                                        type: 'error',
                                        offset:320
                                    });
                                }

                            })
                        }
                })

            }else{  //如果没有ID就新增数据
                this.$refs['formDataRef'].validate((valid) => {
                    if (valid) {
                        let url = base + "/admin/add";
                        this.btnLoading = true;
                        request.post(url,this.formData).then(res => {
                            console.log(res);
                            if(res.code==200){
                                this.$message({
                                    message: '操作成功',
                                    type: 'success',
                                    offset:320
                                });
                                this.btnLoading = false;
                                this.formVisible = false;
                                this.getDatas();
                                this.$refs['formDataRef'].resetFields();
                                this.formData={};
                            } else{
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

        },
        handleDelete(index,row){
            this.$confirm('确认删除该记录吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.listLoading = true;
                let url = base + "/admin/del?id=" + row.id;
                request.post(url).then(res => {
                    this.listLoading = false;
                    //NProgress.done();
                    this.$message({
                        message: '删除成功',
                        type: 'success',
                        offset:320
                    });
                    this.getDatas();
                })

            }).catch(() => {

            });
        },
        handleCurrentChange(val) {
            this.page.currentPage = val;
            this.getDatas();
        },
        query(){   //查询
            this.getDatas();
        },
        //获取列表数据
        getDatas() {
            let para = {
                username:this.filters.username,
                name:this.filters.name,
                utype:this.filters.utype
            };
            this.listLoading = true;
            let url = base + "/admin/list?currentPage=" + this.page.currentPage;
            console.log(url);
            request.post(url,para).then(res => {
                    console.log(res);
                if(res.resdata.length>0){
                    this.isPage = true;
                }else{
                    this.isPage = false;
                }
                this.page.totalCount = res.count;
                this.datalist = res.resdata;
                this.listLoading = false;
            })
        },
    }
}
</script>
