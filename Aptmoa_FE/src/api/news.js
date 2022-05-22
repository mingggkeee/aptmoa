import { apiInstance } from "./index.js";

const api = apiInstance();

function listNews(param, success, fail) {
  api
    .get(`/news`, { params: param })
    .then(success)
    .catch(fail);
}

function getNews(newsno, success, fail) {
  api
    .get(`/news/${newsno}`)
    .then(success)
    .catch(fail);
}

function newsCrawl(regtime, success, fail) {
  api
    .post(`/news/crawl`, JSON.stringify(regtime))
    .then(success)
    .catch(fail);
}

export { listNews, getNews, newsCrawl };
