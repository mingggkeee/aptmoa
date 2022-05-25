<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 id="category_title">
      LIST OF INTEREST
    </h3>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "Interest"
};
</script>

<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}

#category_title {
  font-family: "Changa One", cursive;
  font-size: 50px;
  background: #f2f2f2;
  color: #364a73;
}
</style>
