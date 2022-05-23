import {
  sidoList,
  gugunList,
  apartList,
  apartListByName
} from "@/api/apart.js";

const apartStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시.도" }],
    sidosName: [{ value: null, text: "시.도" }],
    guguns: [{ value: null, text: "구.군" }],
    // date: [{ value: null, text: "거래일시" }],
    aparts: [],
    nameApts: [],
    apart: null,
    pagination: {
      date: null,
      gugunCode: null,
      rows: 100,
      perPage: 10,
      currentPage: 1
    }
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach(sido => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },

    SET_SIDO_NAME_LIST: (state, sidosName) => {
      sidosName.forEach(sido => {
        state.sidosName.push({ value: sido.sidoName, text: sido.sidoName });
      });
    },
    SET_SIDO(state, payload) {
      state.sido = { code: payload[1], name: payload[0] };
      0;
    },

    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach(gugun => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_SIDO_LIST: state => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_SIDO_NAME_LIST: state => {
      state.sidosName = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: state => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    SET_APART_LIST: (state, aparts) => {
      //   console.log(houses);
      state.aparts = aparts;
    },
    SET_APART_NAME_LIST: (state, nameApts) => {
      //   console.log(houses);
      state.nameApts = nameApts;
    },
    SET_DETAIL_APART: (state, apart) => {
      state.apart = apart;
    },
    SET_PAGINATION: (state, pagination) => {
      state.pagination = pagination;
      console.log(state.pagination);
    }
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getSidoName: ({ commit }) => {
      sidoList(
        ({ data }) => {
          console.log(data);
          commit("SET_SIDO_NAME_LIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getApartList: ({ commit }, param) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      //   const SERVICE_KEY =
      //     "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
      let DEAL_YMD = "202110";
      let numOfRows = "10";
      if (param.date && param.date !== "") DEAL_YMD = param.date;
      if (param.rows && param.rows !== "") numOfRows = param.rows;
      console.log(param);
      console.log(DEAL_YMD + ", " + numOfRows);
      const params = {
        LAWD_CD: param.gugunCode,
        numOfRows: numOfRows,
        pageNo: param.page,
        DEAL_YMD: DEAL_YMD,
        serviceKey: decodeURIComponent(SERVICE_KEY)
      };
      apartList(
        params,
        response => {
          console.log(response);
          const body = response.data.response.body;
          let data;
          let pagination;
          if (!Array.isArray(body.items.item)) {
            data = [];
            data.push(body.items.item);
          } else data = body.items.item;
          console.log(data);
          pagination = {
            date: param.date,
            gugunCode: param.gugunCode,
            rows: body.totalCount,
            perPage: body.numOfRows,
            currentPage: body.pageNo
          };
          commit("SET_APART_LIST", data);
          commit("SET_PAGINATION", pagination);
        },
        error => {
          console.log(error);
        }
      );
    },
    getApartListByName: ({ commit }, params) => {
      apartListByName(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_APART_NAME_LIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    detailApart: ({ commit }, apart) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_APART", apart);
    }
  }
};

export default apartStore;
