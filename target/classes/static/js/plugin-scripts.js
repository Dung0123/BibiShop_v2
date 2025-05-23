function loadScript(src) {
    return new Promise((resolve, reject) => {
        const script = document.createElement('script');
        script.src = src;
        script.onload = resolve;  // Khi script tải xong, resolve Promise
        script.onerror = reject;  // Nếu có lỗi, reject Promise
        document.head.appendChild(script);
    });
}

async function loadScripts() {
    try {
        await loadScript("/js/jquery-3.1.1.min.js");

        // Bootstrap - Phụ thuộc jQuery
        await loadScript("/js/bootstrap.min.js");

        // MetisMenu & Slimscroll - Phụ thuộc jQuery
        await loadScript("/js/plugins/metisMenu/jquery.metisMenu.js");
        await loadScript("/js/plugins/slimscroll/jquery.slimscroll.min.js");

        // Flot charts - Phụ thuộc jQuery
        await loadScript("/js/plugins/flot/jquery.flot.js");
        await loadScript("/js/plugins/flot/jquery.flot.tooltip.min.js");
        await loadScript("/js/plugins/flot/jquery.flot.spline.js");
        await loadScript("/js/plugins/flot/jquery.flot.resize.js");
        await loadScript("/js/plugins/flot/jquery.flot.pie.js");
        await loadScript("/js/plugins/flot/jquery.flot.symbol.js");
        await loadScript("/js/plugins/flot/jquery.flot.time.js");

        // Flot demo
        await loadScript("/js/demo/flot-demo.js");

        // Peity charts - Phụ thuộc jQuery
        await loadScript("/js/plugins/peity/jquery.peity.min.js");
        await loadScript("/js/demo/peity-demo.js");

        // Custom JS
        await loadScript("/js/inspinia.js");
        await loadScript("/js/plugins/pace/pace.min.js");

        // jQuery UI
        await loadScript("/js/plugins/jquery-ui/jquery-ui.min.js");

        // Jvectormap
        await loadScript("/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js");
        await loadScript("/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js");

        // EasyPieChart
        await loadScript("/js/plugins/easypiechart/jquery.easypiechart.js");

        // Sparkline
        await loadScript("/js/plugins/sparkline/jquery.sparkline.min.js");
        await loadScript("/js/demo/sparkline-demo.js");


        //toastr

        await loadScript("/shop/toastr/toastr.min.js");

		await loadScript()
        // Khởi tạo MetisMenu sau khi tất cả các script đã được tải
        document.addEventListener('DOMContentLoaded', function() {
            $('#side-menu').metisMenu();
        });

    } catch (error) {
        console.error('Error loading scripts:', error);
    }
}

// Gọi hàm tải các script
loadScripts();
