export { VIEW };
import {REST} from './rest.js';

const VIEW = {
    loadPosts: loadPosts,
    loadPost: loadPost,
    loadCategories: loadCategories,
    loadCategory:loadCategory,
    htmlShow:htmlShow
};

function loadPosts(posts) {

    let mainContent = document.getElementById('mainContent');
    mainContent.innerHTML = '';

    for (const postObj of posts) {
        let post = document.createElement('article');
        post.className = 'post';

        // Create header
        let header = document.createElement('header');
        header.className = 'entry-header';

        let headerH1 = document.createElement('h1');
        headerH1.className = 'entry-title';
        let hrefTitle = document.createElement('a');
        hrefTitle.innerHTML = postObj.title;
        hrefTitle.href = `#post_${postObj.id}`;
        headerH1.appendChild(hrefTitle);
        header.appendChild(headerH1);

        let headerDivMeta = document.createElement('div');
        headerDivMeta.className = 'entry-meta';

        for (const cat of postObj.categories) {
            let spanCategory = document.createElement('span');
            spanCategory.className = 'post-category';
            
            let hrefCategory = document.createElement('a');
            hrefCategory.innerHTML = cat.name;
            hrefCategory.href = `#category_${cat.id}`;
            spanCategory.appendChild(hrefCategory);
            headerDivMeta.appendChild(spanCategory);
        }

        let spanDate = document.createElement('span');
        spanDate.className = 'post-date';
        const dateTime = postObj.timestamp.substring(0, 16);
        spanDate.innerHTML = `<time class="entry-date" datetime="${postObj.timestamp}">${dateTime}</time>`;
        headerDivMeta.appendChild(spanDate);

        let spanComments = document.createElement('span');
        spanComments.className = 'comments-link';
        spanComments.innerHTML = `${postObj.commentsCount} comments`;
        headerDivMeta.appendChild(spanComments);
        header.appendChild(headerDivMeta);

        // Create content div
        let contentDiv = document.createElement('div');
        contentDiv.className = 'entry-content clearfix';
        let pDescription = document.createElement('p');
        pDescription.innerHTML = postObj.description;
        contentDiv.appendChild(pDescription);

        let readMoreDiv = document.createElement('div');
        readMoreDiv.className = 'read-more cl-effect-14';
        readMoreDiv.innerHTML = `<a href="#post_${postObj.id}" class="more-link">Continue reading <span class="meta-nav">→</span></a>`;
        contentDiv.appendChild(readMoreDiv);

        // Build new post
        post.appendChild(header);
        post.appendChild(contentDiv);

        // Add new post
        mainContent.appendChild(post);
    }

}

function loadPost(postObj) {

    let mainContent = document.getElementById('mainContent');
    mainContent.innerHTML = '';
    loadCategories(postObj.categories, true);

    let post = document.createElement('article');
    post.className = 'post';
    //post.innerHTML = '';

    // Create header
    let header = document.createElement('header');
    header.className = 'entry-header';

    let headerH1 = document.createElement('h1');
    headerH1.className = 'entry-title';
    headerH1.textContent = postObj.title;
    header.appendChild(headerH1);

    let headerDivMeta = document.createElement('div');
    headerDivMeta.className = 'entry-meta';

    for (const cat of postObj.categories) {
        let spanCategory = document.createElement('span');
        spanCategory.className = 'post-category';

        let hrefCategory = document.createElement('a');
        hrefCategory.innerHTML = cat.name;
        hrefCategory.href = `#category_${cat.id}`;
        spanCategory.appendChild(hrefCategory);
        headerDivMeta.appendChild(spanCategory);
    }

    let spanDate = document.createElement('span');
    spanDate.className = 'post-date';
    const dateTime = postObj.timestamp.substring(0, 16);
    spanDate.innerHTML = `<time class="entry-date" datetime="${postObj.timestamp}">${dateTime}</time>`;
    headerDivMeta.appendChild(spanDate);

    let spanAuthor = document.createElement('span');
    spanAuthor.className = 'post-author';
    spanAuthor.innerHTML = postObj.author;
    headerDivMeta.appendChild(spanAuthor);

    header.appendChild(headerDivMeta);

    // Create content div
    let contentDiv = document.createElement('div');
    contentDiv.className = 'entry-content clearfix';
    let pPost = document.createElement('p');
    pPost.innerHTML = postObj.post;
    contentDiv.appendChild(pPost);

    let tagsDiv = document.createElement('div');
    tagsDiv.innerHTML = `Tags: ${postObj.tags}`;
    tagsDiv.className = "tags";
    contentDiv.appendChild(tagsDiv);

    contentDiv.appendChild(document.createElement('hr'));

    // Comments form
    let newCommentHeader = document.createElement('div');
    newCommentHeader.className = "newComment";
    newCommentHeader.textContent = "New comment";
    contentDiv.appendChild(newCommentHeader);

    let form = document.createElement('form');
    form.className = 'comment-form';

    let divNick = document.createElement('div');
    divNick.textContent = `Name:`;
    form.appendChild(divNick);

    let nickInput = document.createElement('input');
    nickInput.type = 'text';
    form.appendChild(nickInput);
    form.appendChild(document.createElement('br'));

    let commentSource = document.createElement('div');
    commentSource.textContent = `Comment:`;
    form.appendChild(commentSource);

    let textArea = document.createElement('textarea');
    form.appendChild(textArea);

    form.appendChild(document.createElement('br'));
    let buttonSend = document.createElement('button');
    buttonSend.textContent = 'Send';
    buttonSend.onclick = ()=>{
        let map = new Map();
        map.set("post", postObj.id);
        map.set("name", nickInput.value);
        map.set("comment", textArea.value);
        REST.post('/comment', map, (response)=>{
            REST.get('/comment', (comments)=>{
                nickInput.value = '';
                textArea.value = '';
                loadComments(contentDiv, JSON.parse(comments));
            });
        });
    };
    form.appendChild(buttonSend);
    contentDiv.appendChild(form);
    contentDiv.appendChild(document.createElement('hr'));

    // Comments
    loadComments(contentDiv, postObj.comments);
  
    //
    post.appendChild(header);
    post.appendChild(contentDiv);

    // Add new post
    mainContent.appendChild(post);
}

function loadComments(contentDiv, comments) {

    let commentDiv = document.getElementById('comments');
    if (commentDiv == null) {
        commentDiv = document.createElement('div');
        commentDiv.id = 'comments';
    } else
        commentDiv.innerHTML = ''; // Clear

    if (comments != null) {
        for (const comment of comments) {
            let divNick = document.createElement('div');
            divNick.className = 'name';
            divNick.textContent = `${comment.name}`;
            commentDiv.appendChild(divNick);

            let divCommentTime = document.createElement('div');
            divCommentTime.className = 'commentTime';
            let timeComment = comment.timestamp.substring(0, 16);
            divCommentTime.innerHTML = `${timeComment}`;
            commentDiv.appendChild(divCommentTime);

            commentDiv.appendChild(document.createElement('br'));

            let divCommentSource = document.createElement('p');
            divCommentSource.innerHTML = `${comment.comment}`;
            commentDiv.appendChild(divCommentSource);
            commentDiv.appendChild(document.createElement('hr'));
        }
    }
    contentDiv.appendChild(commentDiv);
}

function loadCategories(categoriesObject, check = false) {

    let divCategories = document.getElementById('categories');
    if (check && divCategories.innerHTML == '')
        return;

    divCategories.innerHTML = '';

    for (const category of categoriesObject) {
        let il = document.createElement('li');
        let href = document.createElement('a');
        href.href = `#category_${category.id}`;
        href.textContent = category.name;
        il.appendChild(href);
        divCategories.appendChild(il);
    }
    
}

function loadCategory(category) {
    loadPosts(category.posts);
}

function htmlShow(infoString) {
    let mainContent = document.getElementById('mainContent');
    mainContent.innerHTML = infoString;
}