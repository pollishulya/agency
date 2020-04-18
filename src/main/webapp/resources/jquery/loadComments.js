var loadPage = 0;
var idComment = 0;

function loadComments(id) {
    $.ajax({
        type: 'GET',
        url: "/comment/comments?foodId=" + id + "&pageNumber=" + loadPage,
        success: function (data) {
            $("#nextBtn").attr("disabled", false);
            if (loadPage == 0) {
                $("#previousBtn").attr("disabled", true);
            } else {
                $("#previousBtn").attr("disabled", false);
            }
            if (data.length < 7) {
                $("#nextBtn").attr("disabled", true);
            } else {
                $("#nextBtn").attr("disabled", false);
            }
            $('#comments').empty();
            var rowsHtml = " <div id='foodComment' class='panel-body'>";
            for (var j = 0; j < data.length; j++) {
                idComment = data[j].id;
                rowsHtml += "<div class='media-block'>";
                rowsHtml += "<a class='media-left' href='#'><img class='img-circle img-sm' src='/resources/images/user.png'></a>";
                rowsHtml += "<div class='media-body'><div class='mar-btm'>";
                rowsHtml += "<p class='text-semibold media-heading box-inline'>" + data[j].username + "</p>";
                rowsHtml += "<div class='starrr disabled stars-existing' data-rating=" + data[j].rating + "></div>";
                rowsHtml += "<p class='text-muted text-sm'><i class='fa fa-mobile fa-lg'></i>" + data[j].time + "</p></div>";
                rowsHtml += "<p>" + data[j].message + "</p>";
                rowsHtml += "</div></div><hr>";
            }
            rowsHtml += "</div>";
            $('#comments').append(rowsHtml);


        },
        error: function (res) {
            if (loadPage == 0) {
                $("#previousBtn").attr("disabled", true);
            }
            $("#nextBtn").attr("disabled", true);
        }

    });

}

function loadDescription(id) {
    $.get("/description?foodId=" + id, function (data) {
        var rowsHtml = "<ul id='list' style='list-style-type:none'><li>";
        for (var j = 0; j < data.length; j++) {
            rowsHtml += "<ul style='list-style-type:none'><div class='alert-day'>" + (j + 1) + " день</div>";
            rowsHtml += "<li>" + data[j].description + "</li>";
            rowsHtml += "</ul>"
        }
        rowsHtml += "</li></ul>";
        $('#description').append(rowsHtml);
    });
}

$(document).ready(function () {
    $('#list > li').click(function (event) {
        $(this).children("ul").slideToggle();
        event.stopPropagation();
    });
});

function saveComment(id) {
    $('#saveCommentMistake').hide();
    var message = $("#comment").val();
    var rating = $('#count').text();
    var comment = ({"message": message, "rating": rating, "foodId": id});
    $.ajax({
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(comment),
        url: '/comment/save',
        success: function (res) {
            var url = window.location.href;
            window.location.replace(url);

        },
        error: function (res) {
            $('#comment').val(res.responseJSON.message);
            $('#sendButton').empty();
            $('#sendButton').append('<button class="btn btn-sm btn-primary pull-right" onclick="updateComment(' + id + ','+res.responseJSON.id+')" type="submit"> <i class="fa fa-pencil fa-fw"></i>update </button>');

            $('#saveCommentMistake').show();
        }

    });
}

function reserveForm() {
    $("#ReserveForm")[0].reset();
    $("#ReserveModal").modal();

}

function reserve(id) {
//alert(id);
    var username = $("#name").val();
    var name = $("#foodName").text();
    var phone = $("#phone").val();
    var numberPerson = $("#numberPersons").val();
    var reservation = ({
        "numberPerson": numberPerson,
        "foodId": id,
        "username": username,
        "phone": phone,
        "nameFood": name
    });
    alert (JSON.stringify(reservation));
    $.ajax({
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(reservation),
        url: '/food/reservation',
        success: function (res) {
            $("#ReserveModal").modal("hide");
        },
        error: function (res) {
        }
    });
};

function stars() {
    var __slice = [].slice;

    (function ($, window) {
        var Starrr;

        Starrr = (function () {
            Starrr.prototype.defaults = {
                rating: void 0,
                numStars: 5,
                change: function (e, value) {
                }
            };

            function Starrr($el, options) {
                var i, _, _ref,
                    _this = this;

                this.options = $.extend({}, this.defaults, options);
                this.$el = $el;
                _ref = this.defaults;
                for (i in _ref) {
                    _ = _ref[i];
                    if (this.$el.data(i) != null) {
                        this.options[i] = this.$el.data(i);
                    }
                }
                this.createStars();
                this.syncRating();
                this.$el.on('mouseover.starrr', 'span', function (e) {
                    return _this.syncRating(_this.$el.find('span').index(e.currentTarget) + 1);
                });
                this.$el.on('mouseout.starrr', function () {
                    return _this.syncRating();
                });
                this.$el.on('click.starrr', 'span', function (e) {
                    return _this.setRating(_this.$el.find('span').index(e.currentTarget) + 1);
                });
                this.$el.on('starrr:change', this.options.change);
            }

            Starrr.prototype.createStars = function () {
                var _i, _ref, _results;

                _results = [];
                for (_i = 1, _ref = this.options.numStars; 1 <= _ref ? _i <= _ref : _i >= _ref; 1 <= _ref ? _i++ : _i--) {
                    _results.push(this.$el.append("<span class='glyphicon .glyphicon-star-empty'></span>"));
                }
                return _results;
            };

            Starrr.prototype.setRating = function (rating) {
                if (this.options.rating === rating) {
                    rating = void 0;
                }
                this.options.rating = rating;
                this.syncRating();
                return this.$el.trigger('starrr:change', rating);
            };

            Starrr.prototype.syncRating = function (rating) {
                var i, _i, _j, _ref;

                rating || (rating = this.options.rating);
                if (rating) {
                    for (i = _i = 0, _ref = rating - 1; 0 <= _ref ? _i <= _ref : _i >= _ref; i = 0 <= _ref ? ++_i : --_i) {
                        this.$el.find('span').eq(i).removeClass('glyphicon-star-empty').addClass('glyphicon-star');
                    }
                }
                if (rating && rating < 5) {
                    for (i = _j = rating; rating <= 4 ? _j <= 4 : _j >= 4; i = rating <= 4 ? ++_j : --_j) {
                        this.$el.find('span').eq(i).removeClass('glyphicon-star').addClass('glyphicon-star-empty');
                    }
                }
                if (!rating) {
                    return this.$el.find('span').removeClass('glyphicon-star').addClass('glyphicon-star-empty');
                }
            };

            return Starrr;

        })();
        return $.fn.extend({
            starrr: function () {
                var args, option;

                option = arguments[0], args = 2 <= arguments.length ? __slice.call(arguments, 1) : [];
                return this.each(function () {
                    var data;

                    data = $(this).data('star-rating');
                    if (!data) {
                        $(this).data('star-rating', (data = new Starrr($(this), option)));
                    }
                    if (typeof option === 'string') {
                        return data[option].apply(data, args);
                    }
                });
            }
        });
    })(window.jQuery, window);

    $(function () {
        return $(".starrr").starrr();
    });

    $(document).ready(function () {

        $('#stars').on('starrr:change', function (e, value) {
            $('#count').html(value);
        });

        $('.stars-existing').on('starrr:change', function (e, value) {
            $('#count-existing').html(value);
        });

    });
}

function updateComment(id,commentId) {
    $('#saveCommentMistake').hide();
    var message = $("#comment").val();
    var rating = $('#count').text();
    var comment = ({"id":commentId,"message": message, "rating": rating, "foodId": id});
    $.ajax({
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(comment),
        url: '/comment/update/save',
        success: function (res) {
            var url = window.location.href;
            window.location.replace(url);

        },
        error: function (res) {
            $('#saveCommentMistake').show();
        }

    });
}



