// // jQuery - Tải đầu tiên
// var script1 = document.createElement('script');
// script1.src = "/js/jquery-3.1.1.min.js";
// document.head.appendChild(script1);
//
// // Bootstrap - Phụ thuộc vào jQuery
// var script2 = document.createElement('script');
// script2.src = "/js/bootstrap.min.js";
// document.head.appendChild(script2);
//
// // MetisMenu - Phụ thuộc vào jQuery
// var script3 = document.createElement('script');
// script3.src = "/js/plugins/metisMenu/jquery.metisMenu.js";
// document.head.appendChild(script3);
//
// // Slimscroll - Phụ thuộc vào jQuery
// var script4 = document.createElement('script');
// script4.src = "/js/plugins/slimscroll/jquery.slimscroll.min.js";
// document.head.appendChild(script4);
//
// // Flot charts - Phụ thuộc vào jQuery
// var script5 = document.createElement('script');
// script5.src = "/js/plugins/flot/jquery.flot.js";
// document.head.appendChild(script5);
//
// var script6 = document.createElement('script');
// script6.src = "/js/plugins/flot/jquery.flot.tooltip.min.js";
// document.head.appendChild(script6);
//
// var script7 = document.createElement('script');
// script7.src = "/js/plugins/flot/jquery.flot.spline.js";
// document.head.appendChild(script7);
//
// var script8 = document.createElement('script');
// script8.src = "/js/plugins/flot/jquery.flot.resize.js";
// document.head.appendChild(script8);
//
// var script9 = document.createElement('script');
// script9.src = "/js/plugins/flot/jquery.flot.pie.js";
// document.head.appendChild(script9);
//
// var script10 = document.createElement('script');
// script10.src = "/js/plugins/flot/jquery.flot.symbol.js";
// document.head.appendChild(script10);
//
// var script11 = document.createElement('script');
// script11.src = "/js/plugins/flot/jquery.flot.time.js";
// document.head.appendChild(script11);
//
// // Peity charts - Phụ thuộc vào jQuery
// var script12 = document.createElement('script');
// script12.src = "/js/plugins/peity/jquery.peity.min.js";
// document.head.appendChild(script12);
//
// var script13 = document.createElement('script');
// script13.src = "/js/demo/peity-demo.js";
// document.head.appendChild(script13);
//
// // Custom and plugin javascript
// var script14 = document.createElement('script');
// script14.src = "/js/inspinia.js";
// document.head.appendChild(script14);
//
// var script15 = document.createElement('script');
// script15.src = "/js/plugins/pace/pace.min.js";
// document.head.appendChild(script15);
//
// // jQuery UI - Phụ thuộc vào jQuery
// var script16 = document.createElement('script');
// script16.src = "/js/plugins/jquery-ui/jquery-ui.min.js";
// document.head.appendChild(script16);
//
// // Jvectormap - Phụ thuộc vào jQuery
// var script17 = document.createElement('script');
// script17.src = "/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js";
// document.head.appendChild(script17);
//
// var script18 = document.createElement('script');
// script18.src = "/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js";
// document.head.appendChild(script18);
//
// // EasyPieChart - Phụ thuộc vào jQuery
// var script19 = document.createElement('script');
// script19.src = "/js/plugins/easypiechart/jquery.easypiechart.js";
// document.head.appendChild(script19);
//
// // Sparkline - Phụ thuộc vào jQuery
// var script20 = document.createElement('script');
// script20.src = "/js/plugins/sparkline/jquery.sparkline.min.js";
// document.head.appendChild(script20);
//
// // Sparkline demo data
// var script21 = document.createElement('script');
// script21.src = "/js/demo/sparkline-demo.js";
// document.head.appendChild(script21);
function loadScript(src) {
    const script = document.createElement('script');
    script.src = src;
    document.head.appendChild(script);
}

// jQuery - Tải đầu tiên
loadScript("/js/jquery-3.1.1.min.js");

// Bootstrap - Phụ thuộc jQuery
loadScript("/js/bootstrap.min.js");

// MetisMenu & Slimscroll - Phụ thuộc jQuery
loadScript("/js/plugins/metisMenu/jquery.metisMenu.js");
loadScript("/js/plugins/slimscroll/jquery.slimscroll.min.js");

// Flot charts - Phụ thuộc jQuery
loadScript("/js/plugins/flot/jquery.flot.js");
loadScript("/js/plugins/flot/jquery.flot.tooltip.min.js");
loadScript("/js/plugins/flot/jquery.flot.spline.js");
loadScript("/js/plugins/flot/jquery.flot.resize.js");
loadScript("/js/plugins/flot/jquery.flot.pie.js");
loadScript("/js/plugins/flot/jquery.flot.symbol.js");
loadScript("/js/plugins/flot/jquery.flot.time.js");

// Flot demo
loadScript("/js/demo/flot-demo.js");

// Peity charts - Phụ thuộc jQuery
loadScript("/js/plugins/peity/jquery.peity.min.js");
loadScript("/js/demo/peity-demo.js");

// Custom JS
loadScript("/js/inspinia.js");
loadScript("/js/plugins/pace/pace.min.js");

// jQuery UI
loadScript("/js/plugins/jquery-ui/jquery-ui.min.js");

// Jvectormap
loadScript("/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js");
loadScript("/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js");

// EasyPieChart
loadScript("/js/plugins/easypiechart/jquery.easypiechart.js");

// Sparkline
loadScript("/js/plugins/sparkline/jquery.sparkline.min.js");
loadScript("/js/demo/sparkline-demo.js");
