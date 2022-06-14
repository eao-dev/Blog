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
    if (mainContent == null)
        throw "div 'mainContent' not found";
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

        let spanAuthor = document.createElement('span');
        spanAuthor.className = 'post-author';
        spanAuthor.innerHTML = postObj.author;
        headerDivMeta.appendChild(spanAuthor);

        let nComments = 0;
        if (postObj.comments != null)
            nComments = postObj.comments.length;

        let spanComments = document.createElement('span');
        spanComments.className = 'comments-link';
        spanComments.innerHTML = `${nComments} comments`;
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
    if (mainContent == null)
        throw "div 'mainContent' not found";
    mainContent.innerHTML = '';

    let post = document.createElement('article');
    post.className = 'post';

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
        REST.post('/comment', map, (status)=>{
            if (status!=201)
                throw `Error send comment. HTTP status: ${status}`;
                location.reload();
        });
    };
    form.appendChild(buttonSend);
    contentDiv.appendChild(form);
    contentDiv.appendChild(document.createElement('hr'));

    // Comments
    if (postObj.comments != null) {
        for (const comment of postObj.comments) {
            let divNick = document.createElement('div');
            divNick.className = 'name';
            divNick.textContent = `${comment.name}`;
            contentDiv.appendChild(divNick);

            let divCommentTime = document.createElement('div');
            divCommentTime.className = 'commentTime';
            let timeComment = comment.timestamp.substring(0, 16);
            divCommentTime.innerHTML = `${timeComment}`;
            contentDiv.appendChild(divCommentTime);

            contentDiv.appendChild(document.createElement('br'));

            let divCommentSource = document.createElement('p');
            divCommentSource.innerHTML = `${comment.comment}`;
            contentDiv.appendChild(divCommentSource);

            contentDiv.appendChild(document.createElement('hr'));
        }
    }

  
    //
    post.appendChild(header);
    post.appendChild(contentDiv);

    // Add new post
    mainContent.appendChild(post);

}

function loadCategories(categoriesObject) {

    let divCategories = document.getElementById('categories');
    if (divCategories==null)
        throw "div 'categories' not found";

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
    if (category == null)
        throw "Category is null";
    loadPosts(category.posts);
}

function htmlShow(infoString) {

    let mainContent = document.getElementById('mainContent');
    if (mainContent == null)
        throw "div 'mainContent' not found";
    mainContent.innerHTML = '';

    mainContent.innerHTML = infoString;

}