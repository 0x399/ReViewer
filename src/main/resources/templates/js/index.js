function redirect() {
   
    window.location.href = "create-user.html";
    
  }

  $(document).ready(function() {
    $('.gameimg').hover(
      function() {
        $(this).css('transform', 'scale(1.2)');
      },
      function() {
        $(this).css('transform', 'scale(1)');
      }
    );
  });
  