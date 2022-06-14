import {REST} from './rest.js';
import {VIEW} from './view.js';
import {ROUTE} from './route.js';

function getAll() {
    // Load all posts
    REST.get('/post/', (status, response)=> {
        if (status == 200) {
            const posts = JSON.parse(response);
            VIEW.loadPosts(posts);
            return;
        }
        throw `Error get posts. HTTP status: ${status}`;
    });
    
    // Load all categories
    REST.get('/category/', (status, response)=> {
        if (status == 200) {
            const posts = JSON.parse(response);
            VIEW.loadCategories(posts);
            return;
        }
        throw `Error get posts. HTTP status: ${status}`;
    });

}

function loadResource(param) {
    if (param.substring(0,9) == 'category_') {
        let categoryId = param.replace('category_', '');

        REST.get(`/category/${categoryId}`, (status, response)=> {
            if (status == 200) {
                const category = JSON.parse(response);
                VIEW.loadCategory(category);
                return;
            }
            throw `Error get category. HTTP status: ${status}`;
        });
        return true;
    }

    if (param.substring(0,5) == 'post_') {
            let postId = param.replace('post_', '');

            REST.get(`/post/${postId}/`, (status, response)=> {
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
 
    ROUTE.setMainPage(getAll);
    ROUTE.setOtherHandler(loadResource);
    ROUTE.run();

};