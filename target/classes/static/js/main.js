import {REST} from './rest.js';
import {VIEW} from './view.js';
import {ROUTE} from './route.js';

function getAllPost() {
    REST.get('/post/all/', (status, response)=> {
        if (status == 200) {
            const posts = JSON.parse(response);
            VIEW.loadPosts(posts);
            return;
        }
        throw `Error get posts. HTTP status: ${status}`;
    });
}


function isNum(x) {

    if (x==null || x==undefined || x=='')
        return false;

    return isFinite(x);
}

function loadPost(postId) {
    if (isNum(postId)) {
            REST.get(`/post/read/${postId}/`, (status, response)=> {
            if (status == 200) {
                const post = JSON.parse(response);
                VIEW.loadPost(post);
                return;
            }
            throw `Error get post. HTTP status: ${status}`;
        });

        return true;
    }
    return false;
}


window.onload = function() {
 
    ROUTE.setMainPage(getAllPost);
    ROUTE.setOtherHandler(loadPost);
    ROUTE.run();

};