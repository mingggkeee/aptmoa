import { apiInstance, apartInstance } from "./index.js";

const api = apiInstance();
const apart = apartInstance();

function sidoList(success, fail) {
  api
    .get(`/map/sido`)
    .then(success)
    .catch(fail);
}

function gugunList(params, success, fail) {
  api
    .get(`/map/gugun`, { params: params })
    .then(success)
    .catch(fail);
}

function apartList(params, success, fail) {
  apart
    .get(``, { params: params })
    .then(success)
    .catch(fail);
}

function apartListByName(params, success, fail) {
  api
    .get(`/map/search`, { params: params })
    .then(success)
    .catch(fail);
}

export { sidoList, gugunList, apartList, apartListByName };
