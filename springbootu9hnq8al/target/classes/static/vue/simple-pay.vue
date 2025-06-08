<template>
  <div class="pay-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>扫码支付</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="refreshOrder">刷新状态</el-button>
      </div>
      <div class="pay-content">
        <div class="amount">支付金额：{{ amount }}元</div>
        <div class="order-info" v-if="order">
          <p>订单号：{{ order.id }}</p>
          <p>创建时间：{{ order.createTime }}</p>
          <p>支付状态：{{ orderStatus }}</p>
          <p v-if="order.payTime">支付时间：{{ order.payTime }}</p>
        </div>
        <div class="qrcode" v-if="!isPaid">
          <img :src="qrCode" alt="支付二维码" v-if="qrCode">
          <div v-else class="loading">加载中...</div>
        </div>
        <div class="tips">
          <template v-if="!isPaid">
            <p>请使用支付宝扫码支付</p>
            <p>支付完成后请联系店主确认</p>
          </template>
          <template v-else>
            <p class="success">支付成功！</p>
          </template>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  props: {
    amount: {
      type: [String, Number],
      required: true
    },
    remark: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      qrCode: '',
      orderId: '',
      order: null,
      timer: null
    }
  },
  computed: {
    isPaid() {
      return this.order && this.order.status === 1
    },
    orderStatus() {
      if (!this.order) return '未知'
      return this.order.status === 1 ? '已支付' : '未支付'
    }
  },
  created() {
    this.createOrder()
    // 每30秒刷新一次订单状态
    this.timer = setInterval(this.refreshOrder, 30000)
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer)
    }
  },
  methods: {
    createOrder() {
      this.$http.post('/pay/create', {
        amount: this.amount,
        remark: this.remark
      }).then(res => {
        if (res.data.code === 0) {
          this.qrCode = res.data.qrCode
          this.orderId = res.data.orderId
          this.refreshOrder()
        } else {
          this.$message.error(res.data.msg)
        }
      }).catch(err => {
        this.$message.error('创建订单失败')
      })
    },
    refreshOrder() {
      if (!this.orderId) return
      this.$http.get(`/pay/order/${this.orderId}`).then(res => {
        if (res.data.code === 0) {
          this.order = res.data.order
          if (this.isPaid && this.timer) {
            clearInterval(this.timer)
          }
        }
      })
    }
  }
}
</script>

<style scoped>
.pay-container {
  padding: 20px;
}
.box-card {
  width: 400px;
  margin: 0 auto;
}
.pay-content {
  text-align: center;
}
.amount {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
}
.order-info {
  text-align: left;
  margin: 0 20px 20px;
  padding: 10px;
  background: #f5f7fa;
  border-radius: 4px;
}
.order-info p {
  margin: 5px 0;
  color: #606266;
}
.qrcode {
  width: 200px;
  height: 200px;
  margin: 0 auto 20px;
}
.qrcode img {
  width: 100%;
  height: 100%;
}
.loading {
  line-height: 200px;
  color: #909399;
}
.tips {
  color: #909399;
  font-size: 14px;
}
.tips p {
  margin: 5px 0;
}
.success {
  color: #67C23A;
  font-weight: bold;
}
</style> 