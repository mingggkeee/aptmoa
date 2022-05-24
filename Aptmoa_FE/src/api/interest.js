import { apiInstance } from "./index.js";

const api = apiInstance();

function listInterest(userId, success, fail) {
  api
    .get(`/interest/${userId}`)
    .then(success)
    .catch(fail);
}

function checkInterest(params, success, fail) {
  api
    .get(`/interest/dupcheck`, { params: params })
    .then(success)
    .catch(fail);
}

function writeInterest(interest, success, fail) {
  api
    .post(`/interest`, JSON.stringify(interest))
    .then(success)
    .catch(fail);
}

function deleteInterest(no, success, fail) {
  api
    .delete(`/interest/${no}`)
    .then(success)
    .catch(fail);
}

export { listInterest, writeInterest, deleteInterest, checkInterest };
