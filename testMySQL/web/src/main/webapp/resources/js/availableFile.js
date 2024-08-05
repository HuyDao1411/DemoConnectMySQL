$(document).ready(function() {
    $('#viewModeSelect').change(function() {
        var selectedMode = $(this).val();
        var fileList = $('#fileList');
        if (selectedMode === 'list') {
            fileList.removeClass('large-icon-view').addClass('list-view');
        } else if (selectedMode === 'large-icon') {
            fileList.removeClass('list-view').addClass('large-icon-view');
        }
    });
    $('#addToCartBtn').click(function() {
        var selectedFiles = $('input[type="checkbox"]:checked').length;
        $('#cartCount').text(selectedFiles);
    });
});