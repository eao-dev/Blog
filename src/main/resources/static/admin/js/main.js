import {REST} from './rest.js';
import {VIEW} from './view.js';

window.onload = function() {

// Load all posts
REST.get('/post/', (status, response)=> {
    if (status != 200)
        throw `Error get posts. HTTP status: ${status}`;
    VIEW.loadPosts(JSON.parse(response));      
});

// Load all categories
REST.get('/category/', (status, response)=> {
    if (status != 200)
        throw `Error get categories. HTTP status: ${status}`;
    VIEW.loadCategories(JSON.parse(response));
});
   
};