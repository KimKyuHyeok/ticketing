function checkSessionExpiration() {
    var sessionExpirationTime = new Date('<%= session.getAttribute("expirationTime") %>');
    var currentTime = new Date();
    if (currentTime > sessionExpirationTime) {
        logout();
    }
}