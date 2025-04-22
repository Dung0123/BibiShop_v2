// function loadScript(src) {
//     const script = document.createElement('script');
//     script.src = src;
//     document.head.appendChild(script);
// }
//
// // jQuery - Tải đầu tiên
// loadScript("/js/jquery-3.1.1.min.js");
//
// // Bootstrap - Phụ thuộc jQuery
// loadScript("/js/bootstrap.min.js");
//
// // MetisMenu & Slimscroll - Phụ thuộc jQuery
// loadScript("/js/plugins/metisMenu/jquery.metisMenu.js");
// loadScript("/js/plugins/slimscroll/jquery.slimscroll.min.js");
//
// // Flot charts - Phụ thuộc jQuery
// loadScript("/js/plugins/flot/jquery.flot.js");
// loadScript("/js/plugins/flot/jquery.flot.tooltip.min.js");
// loadScript("/js/plugins/flot/jquery.flot.spline.js");
// loadScript("/js/plugins/flot/jquery.flot.resize.js");
// loadScript("/js/plugins/flot/jquery.flot.pie.js");
// loadScript("/js/plugins/flot/jquery.flot.symbol.js");
// loadScript("/js/plugins/flot/jquery.flot.time.js");
//
// // Flot demo
// loadScript("/js/demo/flot-demo.js");
//
// // Peity charts - Phụ thuộc jQuery
// loadScript("/js/plugins/peity/jquery.peity.min.js");
// loadScript("/js/demo/peity-demo.js");
//
// // Custom JS
// loadScript("/js/inspinia.js");
// loadScript("/js/plugins/pace/pace.min.js");
//
// // jQuery UI
// loadScript("/js/plugins/jquery-ui/jquery-ui.min.js");
//
// // Jvectormap
// loadScript("/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js");
// loadScript("/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js");
//
// // EasyPieChart
// loadScript("/js/plugins/easypiechart/jquery.easypiechart.js");
//
// // Sparkline
// loadScript("/js/plugins/sparkline/jquery.sparkline.min.js");
// loadScript("/js/demo/sparkline-demo.js");
function loadScript(src, callback) {
    const script = document.createElement('script');
    script.src = src;
    script.onload = callback;  // Khi script tải xong, gọi callback
    document.head.appendChild(script);
}

// jQuery - Tải đầu tiên
loadScript("/js/jquery-3.1.1.min.js", function() {
    // Bootstrap - Phụ thuộc jQuery
    loadScript("/js/bootstrap.min.js", function() {
        // MetisMenu & Slimscroll - Phụ thuộc jQuery
        loadScript("/js/plugins/metisMenu/jquery.metisMenu.js", function() {
            loadScript("/js/plugins/slimscroll/jquery.slimscroll.min.js", function() {
                // Flot charts - Phụ thuộc jQuery
                loadScript("/js/plugins/flot/jquery.flot.js", function() {
                    loadScript("/js/plugins/flot/jquery.flot.tooltip.min.js", function() {
                        loadScript("/js/plugins/flot/jquery.flot.spline.js", function() {
                            loadScript("/js/plugins/flot/jquery.flot.resize.js", function() {
                                loadScript("/js/plugins/flot/jquery.flot.pie.js", function() {
                                    loadScript("/js/plugins/flot/jquery.flot.symbol.js", function() {
                                        loadScript("/js/plugins/flot/jquery.flot.time.js", function() {
                                            // Flot demo
                                            loadScript("/js/demo/flot-demo.js", function() {
                                                // Peity charts - Phụ thuộc jQuery
                                                loadScript("/js/plugins/peity/jquery.peity.min.js", function() {
                                                    loadScript("/js/demo/peity-demo.js", function() {
                                                        // Custom JS
                                                        loadScript("/js/inspinia.js", function() {
                                                            loadScript("/js/plugins/pace/pace.min.js", function() {
                                                                // jQuery UI
                                                                loadScript("/js/plugins/jquery-ui/jquery-ui.min.js", function() {
                                                                    // Jvectormap
                                                                    loadScript("/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js", function() {
                                                                        loadScript("/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js", function() {
                                                                            // EasyPieChart
                                                                            loadScript("/js/plugins/easypiechart/jquery.easypiechart.js", function() {
                                                                                // Sparkline
                                                                                loadScript("/js/plugins/sparkline/jquery.sparkline.min.js", function() {
                                                                                    loadScript("/js/demo/sparkline-demo.js", function() {
                                                                                        // Khởi tạo MetisMenu sau khi tất cả các script đã được tải
                                                                                        document.addEventListener('DOMContentLoaded', function() {
                                                                                            $('#side-menu').metisMenu();
                                                                                        });
                                                                                    });
                                                                                });
                                                                            });
                                                                        });
                                                                    });
                                                                });
                                                            });
                                                        });
                                                    });
                                                });
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });
    });
});
