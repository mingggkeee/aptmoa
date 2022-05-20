import { apiInstance } from "./index.js";

const api = apiInstance();

function listQna(param, success, fail) {
  api
    .get(`/qna`, { params: param })
    .then(success)
    .catch(fail);
}

function writeQna(qna, success, fail) {
  api
    .post(`/qna`, JSON.stringify(qna))
    .then(success)
    .catch(fail);
}

function getQna(qnano, success, fail) {
  api
    .get(`/qna/${qnano}`)
    .then(success)
    .catch(fail);
}

function modifyQna(qna, success, fail) {
  api
    .put(`/qna/${qna.qnano}`, JSON.stringify(qna))
    .then(success)
    .catch(fail);
}

function deleteQna(qnano, success, fail) {
  api
    .delete(`/qna/${qnano}`)
    .then(success)
    .catch(fail);
}

export { listQna, writeQna, getQna, modifyQna, deleteQna };
