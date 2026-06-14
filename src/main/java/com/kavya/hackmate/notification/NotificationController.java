package com.kavya.hackmate.notification;

import com.kavya.hackmate.notification.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.kavya.hackmate.notification.dto.UnreadCountResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationResponse> getNotifications(
            Authentication authentication) {

        return notificationService.getNotifications(
                authentication.getName());
    }

    @PatchMapping("/{id}/read")
    public void markAsRead(
            @PathVariable Long id,
            Authentication authentication) {

        notificationService.markAsRead(
                id,
                authentication.getName());
    }

    @GetMapping("/unread-count")
    public UnreadCountResponse getUnreadCount(
            Authentication authentication) {

        return notificationService.getUnreadCount(
                authentication.getName());
    }
}