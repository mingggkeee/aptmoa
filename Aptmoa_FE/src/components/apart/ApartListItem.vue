<!-- <template>
  <v-row
    class="m-2"
    @click="selectApart"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <v-col cols="2" class="text-center align-self-center">
      <v-img
        thumbnail
        src="https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/201809/14/8f85c1c7-7e6c-438a-92ba-24ae1e57615e.jpg"
        alt="Image 1"
      ></v-img>
    </v-col>
    <v-col cols="10" class="align-self-center">
      [{{ apart.일련번호 }}] {{ apart.아파트 }}
    </v-col>
  </v-row>
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
</style> -->
