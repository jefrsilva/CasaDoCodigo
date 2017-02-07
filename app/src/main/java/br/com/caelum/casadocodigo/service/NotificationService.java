package br.com.caelum.casadocodigo.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import br.com.caelum.casadocodigo.event.NotificacaoEvent;

/**
 * Created by jefrsilva on 07/02/17.
 */

public class NotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        EventBus.getDefault().post(new NotificacaoEvent(remoteMessage));
    }
}
