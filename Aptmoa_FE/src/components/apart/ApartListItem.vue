<template>
  <b-row
    class="m-2"
    @click="selectApart"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center">
      [{{ apart.일련번호 }}] {{ apart.아파트 }}
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

const apartStore = "apartStore";

export default {
  name: "ApartListItem",
  data() {
    return {
      isColor: false
    };
  },
  props: {
    apart: Object
  },
  methods: {
    ...mapActions(apartStore, ["detailApart"]),
    selectApart() {
      // console.log("listRow : ", this.apart);
      // this.$store.dispatch("getAaprt", this.apart);
      this.detailApart(this.apart);
    },
    colorChange(flag) {
      this.isColor = flag;
    }
  }
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
