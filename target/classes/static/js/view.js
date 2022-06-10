export {VIEW};

const VIEW = {
    loadPosts:loadPosts,
    loadPost:loadPost,
};

function loadPosts(posts) {

    console.log(posts);

    let mainContent = document.getElementById('mainContent');
    if (mainContent==null)
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
        hrefTitle.href = `#${postObj.id}`;
        headerH1.appendChild(hrefTitle);
        header.appendChild(headerH1);
    
        let headerDivMeta = document.createElement('div');
        headerDivMeta.className = 'entry-meta';
       
        for (const cat of postObj.categories) {
            let spanCategory = document.createElement('span');
            spanCategory.className = 'post-category';
            let hrefCategory = document.createElement('a');
            hrefCategory.innerHTML = cat.name;
            hrefCategory.href = `/categories/${cat.id}`;
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
        if (postObj.comments!=null)
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
        readMoreDiv.innerHTML = `<a href="#${postObj.id}" class="more-link">Continue reading <span class="meta-nav">→</span></a>`;
        contentDiv.appendChild(readMoreDiv);
    
        // Build new post
        post.appendChild(header);
        post.appendChild(contentDiv);
     
        // Add new post
        mainContent.appendChild(post);
    }

}

function loadPost(postObj) {

    console.log(postObj);

    let mainContent = document.getElementById('mainContent');
    if (mainContent==null)
        throw "div 'mainContent' not found";
    mainContent.innerHTML = '';

        let post = document.createElement('article');
        post.className = 'post';
        
        // Create header
        let header = document.createElement('header');
        header.className = 'entry-header';
        
        let headerH1 = document.createElement('h1');
        headerH1.className = 'entry-title';
        let hrefTitle = document.createElement('a');
        hrefTitle.innerHTML = postObj.title;
        hrefTitle.href = `#${postObj.id}`;
        headerH1.appendChild(hrefTitle);
        header.appendChild(headerH1);
    
        let headerDivMeta = document.createElement('div');
        headerDivMeta.className = 'entry-meta';
       
        for (const cat of postObj.categories) {
            let spanCategory = document.createElement('span');
            spanCategory.className = 'post-category';
            let hrefCategory = document.createElement('a');
            hrefCategory.innerHTML = cat.name;
            hrefCategory.href = `/categories/${cat.id}`;
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
        
        // TODO: add tags
        // TODO: add comments

        // Build new post
        post.appendChild(header);
        post.appendChild(contentDiv);
     
        // Add new post
        mainContent.appendChild(post);

}