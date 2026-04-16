


// design issies
// ----------------

// -> code tangling // tight coupling
// -> code scattering // code duplication

function hello(){
    console.log("hello..")
}
function hi(){
    console.log("hi..")
}
function bye(){
    console.log("bye..")
}

// HOF
function withEmoji(fn){
    return function(){
        fn();
        console.log("😀");
    }
}
function withAuth(fn){
    return function(){
        console.log("👮‍♀️")
        fn();
    }
}

hello();
withEmoji(hello)();
withAuth(withEmoji(hello))();