<template>
  <div class="alipay-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>支付宝支付</span>
      </div>
      <el-form :model="payForm" :rules="rules" ref="payForm" label-width="100px">
        <el-form-item label="支付金额" prop="amount">
          <el-input v-model="payForm.amount" placeholder="请输入支付金额"></el-input>
        </el-form-item>
        <el-form-item label="订单标题" prop="subject">
          <el-input v-model="payForm.subject" placeholder="请输入订单标题"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">立即支付</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 支付表单 -->
    <div v-html="payFormHtml" ref="payFormHtml"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      payForm: {
        amount: '',
        subject: ''
      },
      rules: {
        amount: [
          { required: true, message: '请输入支付金额', trigger: 'blur' },
          { pattern: /^\d+(\.\d{1,2})?$/, message: '请输入正确的金额格式', trigger: 'blur' }
        ],
        subject: [
          { required: true, message: '请输入订单标题', trigger: 'blur' }
        ]
      },
      payFormHtml: ''
    }
  },
  methods: {
    submitForm() {
      this.$refs.payForm.validate((valid) => {
        if (valid) {
          this.$http.post('/alipay/create', {
            amount: this.payForm.amount,
            subject: this.payForm.subject
          }).then(res => {
            if (res.data.code === 0) {
              this.payFormHtml = res.data.form;
              // 自动提交表单
              this.$nextTick(() => {
                document.forms[0].submit();
              });
            } else {
              this.$message.error(res.data.msg);
            }
          }).catch(err => {
            this.$message.error('创建支付订单失败');
          });
        }
      });
    }
  }
}
</script>

<style scoped>
.alipay-container {
  padding: 20px;
}
.box-card {
  width: 480px;
  margin: 0 auto;
}
</style> 