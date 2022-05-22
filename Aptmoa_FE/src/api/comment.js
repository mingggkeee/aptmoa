import { apiInstance } from "./index.js";

const api = apiInstance();

function listComment(qnano, success, fail) {
  api
    .get(`/comment/${qnano}`)
    .then(success)
    .catch(fail);
}

function writeComment(comment, success, fail) {
  api
    .post(`/comment`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function deleteComment(commentno, success, fail) {
  api
    .delete(`/comment/${commentno}`)
    .then(success)
    .catch(fail);
}

export { listComment, writeComment, deleteComment };
