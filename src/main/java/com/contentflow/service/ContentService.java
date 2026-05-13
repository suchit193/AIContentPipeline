package com.contentflow.service;

import com.contentflow.dao.ContentDao;
import com.contentflow.model.ContentItem;

public class ContentService {

    GeminiService geminiService =
            new GeminiService();

    ContentDao contentDao =
            new ContentDao();

    // GENERATE CONTENT

    public String generateContent(
            ContentItem item) {

        String aiResponse =

                geminiService.generateContent(

                        item.getPlatform(),

                        item.getBrandName(),

                        item.getBrandDetails(),

                        item.getTargetAudience(),

                        item.getTone(),

                        item.getContentGoal()
                );

        item.setGeneratedContent(
                aiResponse);

        // SAVE TO DATABASE

        contentDao.saveContent(item);

        return aiResponse;
    }
}