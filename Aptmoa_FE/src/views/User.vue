<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h2 class="underline-hotpink">
      <b-icon icon="journals"></b-icon>User Page
    </h2>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "User"
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
</style>
