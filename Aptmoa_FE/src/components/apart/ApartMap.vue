<template>
  <v-container>
    <v-container id="map">
      <ul id="category" style="padding-left: 1230px">
        <li id="BK9" data-order="0">
          <span class="category_bg bank"></span>
          은행
        </li>
        <li id="MT1" data-order="1">
          <span class="category_bg mart"></span>
          마트
        </li>
        <li id="PM9" data-order="2">
          <span class="category_bg pharmacy"></span>
          약국
        </li>
        <li id="OL7" data-order="3">
          <span class="category_bg oil"></span>
          주유소
        </li>
        <li id="CE7" data-order="4">
          <span class="category_bg cafe"></span>
          카페
        </li>
        <li id="CS2" data-order="5">
          <span class="category_bg store"></span>
          편의점
        </li>
        <li id="SW8" data-order="5">
          <span class="category_bg store"></span>
          지하철
        </li>
        <li id="PK6" data-order="5">
          <span class="category_bg store"></span>
          주차장
        </li>
        <li id="AC5" data-order="5">
          <span class="category_bg store"></span>
          학원
        </li>
      </ul>
      <v-container id="menu_wrap" class="bg_white">
        <v-container class="option">
          <v-row no-gutters>
            <v-col align="left">
              <v-text-field
                type="text"
                value="홍대입구"
                id="keyword"
                size="5"
              />
            </v-col>
            <v-col align="right" style="padding-top:10px">
              <v-btn @click="searchPlaces2">검색하기</v-btn>
            </v-col>
          </v-row>
        </v-container>
        <hr />
        <v-container id="pagination"></v-container>
        <ul id="placesList"></ul>
      </v-container>
    </v-container>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";
import { tsImportEqualsDeclaration } from "@babel/types";
const apartStore = "apartStore";
export default {
  name: "KakaoMap",
  data() {
    return {
      selected: "infra",
      map: null,
      marker: [],
      markers: [],
      Addr: [],
      Dong: [],
      index: 0,
      diplayContainer: [],
      infowindow: null,
      markerCategory: "",
      markersCategory: [],
      markerPlace: "",
      markersPlace: [],
      currCategory: "",
      ps: "",
      id: "",
      placeOverlay: "",
      contentNode: "",
      infowin: [],
      infowinPrice: []
    };
  },
  created() {
    const script = document.createElement("script");
    script.onload = () => kakao.maps.load(this.initMap);
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}&libraries=services`;
    document.head.appendChild(script);
  },
  watch: {
    aparts: function(object, old) {
      if (object != null) {
        this.index = object.length;
        this.getAPTData();
      } else {
        console.log(old);
      }
    },
    nameApts: function(object, old) {
      if (object != null) {
        this.index = object.length;
        this.getAPTNameData();
      } else {
        console.log(old);
      }
    }
  },
  computed: {
    ...mapState(apartStore, ["aparts", "nameApts"])
  },
  methods: {
    ...mapActions(apartStore, ["getApartList", "getApartListByName"]),
    initMap() {
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.contentNode = document.createElement("v-container"); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      };
      this.map = new kakao.maps.Map(container, options);
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(position => {
          let lat = position.coords.latitude,
            lon = position.coords.longitude;
          let locPosition = new kakao.maps.LatLng(lat, lon),
            message = '<v-row style="padding:5px;">현재 당신의 위치</v-row>';
          console.log(locPosition);
          this.displayMarker(locPosition, message);
          console.log(locPosition);
        });
      } else {
        let locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
          message = "geolocation을 사용할수 없어요..";
        this.displayMarker(locPosition, message);
      }
      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places(this.map);
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
      this.contentNode.className = "placeinfo_wrap";
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap
      );
      this.placeOverlay.setContent(this.contentNode);
      this.addCategoryClickEvent();
    },
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }
      this.placeOverlay.setMap(null);
      this.removeCategoryMarker();
      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true
      });
    },
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        console.log("데이터");
        console.log(data);
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        console.log(
          " 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요"
        );
      } else if (status === kakao.maps.services.Status.ERROR) {
        console.log(
          "에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요"
        );
      }
    },
    displayPlaces(places) {
      var order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");
      console.log("currCategory?");
      console.log(this.currCategory);
      for (var i = 0; i < places.length; i++) {
        this.markerCategory = this.addMarker2(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order
        );
        ((markerCategory, place) => {
          kakao.maps.event.addListener(markerCategory, "click", () => {
            this.displayPlaceInfo(place);
          });
        })(this.markerCategory, places[i]);
        console.log("places[]", places[0]);
      }
    },
    addMarker2(position, order) {
      // var imageSrc =
      //     "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png",
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
        imageSize = new kakao.maps.Size(24, 35),
        // imageSize = new kakao.maps.Size(27, 28),
        imgOptions = {
          // spriteSize: new kakao.maps.Size(72, 208),
          // spriteOrigin: new kakao.maps.Point(46, order * 36),
          // offset: new kakao.maps.Point(11, 28)
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        );
      this.markerCategory = new kakao.maps.Marker({
        position: position,
        image: markerImage
      });
      this.markerCategory.setMap(this.map);
      this.markersCategory.push(this.markerCategory);
      console.log("markerCategory length");
      console.log(this.markerCategory);
      return this.markerCategory;
    },
    removeMarker() {
      console.log(this.markers);
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
      console.log("removeMarker");
    },
    removeCategoryMarker() {
      for (var i = 0; i < this.markersCategory.length; i++) {
        this.markersCategory[i].setMap(null);
      }
      this.markersCategory = [];
      console.log("removeCategoryMarker");
    },
    displayPlaceInfo(place) {
      var content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";
      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }
      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';
      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },
    addCategoryClickEvent() {
      var category = document.getElementById("category"),
        children = category.children;
      for (var i = 0; i < children.length; i++) {
        children[i].onclick = this.onClickCategory;
      }
    },
    onClickCategory(event) {
      var id = event.currentTarget.id,
        className = event.currentTarget.className;
      this.placeOverlay.setMap(null);
      console.log("className:", className);
      console.log("id:", id);
      if (className === "on") {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removeCategoryMarker();
      } else {
        this.currCategory = id;
        this.changeCategoryClass(this);
        this.searchPlaces();
      }
      console.log("onClickCategory");
    },
    changeCategoryClass(el) {
      var category = document.getElementById("category"),
        children = category.children,
        i;
      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }
      if (el) {
        el.className = "on";
      }
      console.log("changeCategory");
    },
    displayMarker(locPosition, message) {
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: locPosition
      });
      var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;
      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable
      });
      // 인포윈도우를 마커위에 표시합니다
      infowindow.open(this.map, marker);
      // 지도 중심좌표를 접속위치로 변경합니다
      this.map.setCenter(locPosition);
    },
    //---------------------카테고리 끝-----------
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${(size / 8) * 5}px`;
      this.map.relayout();
    },
    getAPTNameData() {
      this.removeMarker();
      console.log("full_address");
      console.warn(full_address);
      let building_code = 0;
      let building_sub_code = 0;
      let full_address = [];

      for (var i = 0; i < this.nameApts.length; i++) {
        // console.warn("getAPTNameData()안에 nameApts : ", this.nameApts[i]);
        building_code = Number.parseInt(this.nameApts[i]["roadNameBonbun"]);
        building_sub_code =
          Number.parseInt(this.nameApts[i]["roadNameBubun"]) + "";
        console.log(building_code);
        if (building_sub_code != 0) {
          building_code += "-" + building_sub_code;
        }
        full_address.push(
          this.nameApts[i]["dong"] +
            " " +
            this.nameApts[i]["roadName"] +
            " " +
            building_code
        );
        this.Addr.push(
          this.nameApts[i]["dong"] +
            " " +
            this.nameApts[i]["roadName"] +
            " " +
            building_code
        );
        this.Dong.push(this.nameApts[i]["dong"]);
        console.log(building_code);
      }
      this.movePosition(full_address[0]);
      for (let i = 0; i < full_address.length; i++) {
        this.setMark(full_address[i]);
      }
    },
    // 아파트 데이터 가져오기
    getAPTData() {
      this.removeMarker();
      this.removeMarker3();
      let building_code = 0;
      let building_sub_code = 0;
      let full_address = [];
      console.log("getAPTData");
      console.log(this.aparts);
      for (var i = 0; i < this.aparts.length; i++) {
        building_code = Number.parseInt(this.aparts[i]["도로명건물본번호코드"]);
        building_sub_code =
          Number.parseInt(this.aparts[i]["도로명건물부번호코드"]) + "";
        console.log(building_code);
        if (building_sub_code != 0) {
          building_code += "-" + building_sub_code;
        }
        full_address.push(
          this.aparts[i]["법정동"] +
            " " +
            this.aparts[i]["도로명"] +
            " " +
            building_code
        );
        this.Addr.push(
          this.aparts[i]["법정동"] +
            " " +
            this.aparts[i]["도로명"] +
            " " +
            building_code
        );
        this.infowin.push(
          this.aparts[i]["아파트"] +
            "@" +
            this.aparts[i]["건축년도"] +
            "@" +
            full_address[i]
        );
        // console.log("getAptData안에 infowin : ", this.infowin[i]);
        this.Dong.push(this.aparts[i]["법정동"]);
        // console.log(building_code);
      }
      // console.log("full_address");
      // console.warn(full_address);
      this.movePosition(full_address[0]);
      for (let i = 0; i < full_address.length; i++) {
        this.setMark(full_address[i], this.infowin[i]);
        // this.infowin.push(this.aparts[i]["아파트"]);
      }
    },
    setMark(address, infowin) {
      console.log("setMark안에 infowin : " + infowin);
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(address, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          var marker = new kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: coords, // 마커를 표시할 위치
            clickable: true //클릭 가능 여부 true
          });
          this.addMarker(marker, result, infowin);
          // console.log("marker", marker);
          console.log("result", result);
        }
      });
    },
    movePosition(address) {
      var geocoder = new kakao.maps.services.Geocoder();
      console.log("지오코더");
      console.log(geocoder);
      geocoder.addressSearch(address, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          var moveLatLon = new kakao.maps.LatLng(result[0].y, result[0].x);
          this.map.setLevel(6);
          this.map.setCenter(moveLatLon);
        }
      });
    },
    addMarker(marker, result, infowin) {
      console.log("addMarker");
      console.log("addMarker안에 infowin : ", infowin);
      kakao.maps.event.addListener(marker, "mouseover", () => {
        this.displayInfoWindow(result, this.index - 1, infowin);
        // this.displayInfoWindow(marker, infowin);

        // console.log("infowin", infowin[this.index - 1]);
      });
      kakao.maps.event.addListener(marker, "mouseout", () => {
        // this.closeOverlay();
        this.infowindow.close();
      });
      // 마커에 click 이벤트를 등록합니다

      // kakao.maps.event.addListener(marker, "click", () => {
      //   //해당 위치의 상세정보를 표현한다.
      // });
      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다
      return marker;
    },
    displayInfoWindow(result, idx, infowin) {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        // this.map.setCenter(this.infowindow.getPosition());
        return;
      }
      console.log("displayInfoWindow안에 infowin : " + infowin);
      var subInfoWin = infowin.split("@");
      // console.log("subInfoWin", subInfoWin[0]);
      // <img src="${imgURL}" width="73" height="70">
      var aptimg =
        "https://cdn.econovill.com/news/photo/202009/412693_346873_1450.jpg";
      var iwContent =
          `<div class="placeinfo">` +
          `<div class="apttitle">` +
          subInfoWin[0] +
          `</div>
          <div class="img">
            <img src="${aptimg}" width="70" height="70" />
          </div>
          <div>
          <span>건축년도 : ` +
          subInfoWin[1] +
          `</span>
          <span>지번 : ` +
          subInfoWin[2] +
          `</span>
          </div>
          `,
        iwPosition = new kakao.maps.LatLng(result[0].y, result[0].x),
        iwRemoveable = true;
      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map,
        position: iwPosition,
        content: iwContent,
        removeable: iwRemoveable
      });
    },
    closeOverlay() {
      this.infowindow.setMap(null);
      console.log("close");
    },
    searchPlaces2() {
      let keyword = document.getElementById("keyword").value;

      if (!keyword.replace(/^\s+|\s+$/g, "")) {
        alert("키워드를 입력해주세요!");
        return false;
      }
      this.removeMarker();
      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      this.ps.keywordSearch(keyword, this.placesSearchCB2);
    },
    placesSearchCB2(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        this.displayPlaces2(data);

        // 페이지 번호를 표출합니다
        this.displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert("검색 결과가 존재하지 않습니다.");
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 결과 중 오류가 발생했습니다.");
        return;
      }
    },
    displayPlaces2(places) {
      var listEl = document.getElementById("placesList"),
        menuEl = document.getElementById("menu_wrap"),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds(),
        listStr = "";

      // 검색 결과 목록에 추가된 항목들을 제거합니다
      this.removeAllChildNods(listEl);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker3();
      // console.log(places.length);
      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
          itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
        // console.log(placePosition);
        this.markerPlace = this.addMarker3(placePosition, i);
        // console.log(this.markerPlace);
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        fragment.appendChild(itemEl);
      }
      // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
      listEl.appendChild(fragment);
      menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      this.map.setBounds(bounds);
    },
    removeMarker3() {
      for (var i = 0; i < this.markersPlace.length; i++) {
        this.markersPlace[i].setMap(null);
      }
      this.markersPlace = [];
    },
    getListItem(index, places) {
      var el = document.createElement("li"),
        itemStr =
          '<span class="markerbg marker_' +
          (index + 1) +
          '"></span>' +
          '<div class="info2">' +
          "   <h5>" +
          places.place_name +
          "</h5>";

      if (places.road_address_name) {
        itemStr += "  <span>" + places.road_address_name + "</span>";
      } else {
        itemStr += "    <span>" + places.address_name + "</span>";
      }

      itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

      el.innerHTML = itemStr;
      el.className = "item";

      return el;
    },
    addMarker3(position, idx, title) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        );
      this.markerPlace = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage
      });
      this.markerPlace.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markersPlace.push(this.markerPlace); // 배열에 생성된 마커를 추가합니다

      return this.markerPlace;
    },
    displayPagination(pagination) {
      var paginationEl = document.getElementById("pagination"),
        fragment = document.createDocumentFragment(),
        i;

      // 기존에 추가된 페이지번호를 삭제합니다
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement("a");
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
          el.className = "on";
        } else {
          el.onclick = (function(i) {
            return function() {
              pagination.gotoPage(i);
            };
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    },
    displayInfowindow(marker, title) {
      var content2 = '<div style="padding:5px;z-index:1;">' + title + "</div>";

      this.infowindow.setContent(content2);
      this.infowindow.open(this.map, marker);
    },
    removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#map {
  width: 1000;
  height: 1000px;
  position: relative;
  overflow: hidden;
}
.button-group {
  margin: 10px 0px;
}
button {
  margin: 0 3px;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
  position: relative;
  z-index: 2;
  background-color: white;
  font-size: 17px;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -120px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .img {
  margin-top: 5px;
}
.placeinfo .apttitle {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #4552d9;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
.info-title {
  display: block;
  background: #50627f;
  color: #fff;
  text-align: center;
  height: 24px;
  line-height: 22px;
  border-radius: 4px;
  padding: 0px 10px;
}
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  text-align: center;
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  margin-top: 12%;
  margin-right: 5%;
  text-align: center;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 24px;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 300px;
  height: 780px;
  margin: 0 0 10px 0;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 2;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info2 {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .info2 {
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
#pagination a {
  display: inline-block;
  margin-right: 10px;
}
#pagination .on {
  font-weight: bold;
  cursor: default;
  color: red;
}
</style>
