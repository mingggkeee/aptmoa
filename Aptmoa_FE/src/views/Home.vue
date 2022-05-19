<template>
  <div id="home">
    <articles>
      <banner />
    </articles>

    <about />

    <contact />
  </div>
</template>

<script>
export default {
  name: "Home",

  components: {
    About: () => import("@/components/home/About"),
    Articles: () => import("@/components/home/Articles"),
    Banner: () => import("@/components/home/Banner"),
    Contact: () => import("@/components/home/Contact")
  }
};
</script>
