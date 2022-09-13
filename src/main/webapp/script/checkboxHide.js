/**
 * 
 */
// $(".button").hide();

$(".checklist").click(function() {
  $('button').toggle( $(".checklist:checked").length > 0 );
});