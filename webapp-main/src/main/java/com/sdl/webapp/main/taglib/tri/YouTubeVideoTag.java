package com.sdl.webapp.main.taglib.tri;

import com.google.common.base.Strings;
import com.sdl.webapp.common.api.MediaHelper;
import com.sdl.webapp.main.markup.html.HtmlAttribute;
import com.sdl.webapp.main.markup.html.HtmlElement;
import com.sdl.webapp.main.markup.html.builders.HtmlBuilders;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.UUID;

public class YouTubeVideoTag extends HtmlElementTag {

    private static final HtmlAttribute CLASS_EMBED_VIDEO_ATTR = new HtmlAttribute("class", "embed-video");

    private static final HtmlElement PLAY_BUTTON_OVERLAY = HtmlBuilders.i().withClass("fa fa-play-circle").build();

    private static final HtmlAttribute ALLOWFULLSCREEN_ATTR = new HtmlAttribute("allowfullscreen", "true");
    private static final HtmlAttribute FRAMEBORDER_ATTR = new HtmlAttribute("frameborder", "0");

    private String youTubeId;
    private String url;
    private String headline;
    private String widthFactor;
    private double aspect;
    private String cssClass;
    private int containerSize;

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setWidthFactor(String widthFactor) {
        this.widthFactor = widthFactor;
    }

    public void setAspect(double aspect) {
        this.aspect = aspect;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public void setContainerSize(int containerSize) {
        this.containerSize = containerSize;
    }

    @Override
    public HtmlElement generateElement() {
        return !Strings.isNullOrEmpty(url) ? getYouTubePlaceholder() : getYouTubeEmbed();
    }

    private HtmlElement getYouTubePlaceholder() {
        final MediaHelper mediaHelper = WebApplicationContextUtils.getRequiredWebApplicationContext(
                pageContext.getServletContext()).getBean(MediaHelper.class);

        final double imageAspect = aspect == 0.0 ? mediaHelper.getDefaultMediaAspect() : aspect;

        final String placeholderImageUrl = mediaHelper.getResponsiveImageUrl(url, widthFactor, imageAspect,
                containerSize);

        return HtmlBuilders.div()
                .withAttribute(CLASS_EMBED_VIDEO_ATTR)
                .withContent(HtmlBuilders.img(placeholderImageUrl).withAlt(headline).build())
                .withContent(HtmlBuilders.button("button")
                        .withAttribute("data-video", youTubeId)
                        .withClass(cssClass)
                        .withContent(PLAY_BUTTON_OVERLAY)
                        .build())
                .build();
    }

    private HtmlElement getYouTubeEmbed() {
        return HtmlBuilders.iframe()
                .withId("video" + UUID.randomUUID().toString())
                .withAttribute("src", "https://www.youtube.com/embed/" + youTubeId + "?version=3&enablejsapi=1")
                .withAttribute(ALLOWFULLSCREEN_ATTR)
                .withAttribute(FRAMEBORDER_ATTR)
                .withClass(cssClass)
                .build();
    }
}