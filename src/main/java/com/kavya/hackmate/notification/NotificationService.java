package com.kavya.hackmate.notification;

import com.kavya.hackmate.notification.enums.NotificationType;
import com.kavya.hackmate.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kavya.hackmate.notification.dto.NotificationResponse;
import com.kavya.hackmate.user.UserRepository;
import com.kavya.hackmate.notification.dto.UnreadCountResponse;

import java.util.List;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public void createNotification(
            User recipient,
            String message,
            NotificationType type) {

        Notification notification = Notification.builder()
                .recipient(recipient)
                .message(message)
                .type(type)
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }

    public List<NotificationResponse> getNotifications(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return notificationRepository
                .findByRecipientOrderByCreatedAtDesc(user)
                .stream()
                .map(notification -> NotificationResponse.builder()
                        .id(notification.getId())
                        .message(notification.getMessage())
                        .type(notification.getType().name())
                        .isRead(notification.getIsRead())
                        .build())
                .toList();
    }

    public void markAsRead(Long notificationId, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (!notification.getRecipient().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        notification.setIsRead(true);

        notificationRepository.save(notification);
    }

    public UnreadCountResponse getUnreadCount(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        long count = notificationRepository
                .countByRecipientAndIsReadFalse(user);

        return UnreadCountResponse.builder()
                .count(count)
                .build();
    }
}