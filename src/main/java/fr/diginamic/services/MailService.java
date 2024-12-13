package fr.diginamic.services;

import brevo.ApiClient;
import brevo.ApiException;
import brevo.Configuration;
import brevo.auth.ApiKeyAuth;
import brevoApi.TransactionalEmailsApi;
import brevoModel.SendSmtpEmail;
import brevoModel.SendSmtpEmailTo;
import fr.diginamic.dto.SimpleInvitationDto;
import fr.diginamic.entities.TentativeChangementMdp;
import fr.diginamic.entities.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MailService {
    @Value("${spring.brevo.key}")
    private String brevoApiKey;

    /**
     * Permet d'envoyer un email de vérfication pour un utilisateur
     * @param utilisateur l'utilisateur concerné
     * @throws ApiException
     */
    public void sendVerificationEmail(Utilisateur utilisateur) throws ApiException {
        setDefaultClient();
        var params = new HashMap<String, String>();
        params.put("lien", utilisateur.getActivationLink().toString());
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        sendSmtpEmail.templateId(3L);
        sendEmail(sendSmtpEmail, utilisateur.getEmail(), params);
    }

    /**
     * Méthode utilitaire pour envoyer l'email
     * @param sendSmtpEmail objet de configuration pour l'envoie du mmail
     * @param email destination
     * @param params paramètres à envoyer à Brevo
     * @throws ApiException
     */
    private void sendEmail(SendSmtpEmail sendSmtpEmail, String email, Map<String, String> params) throws ApiException {
        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
        sendSmtpEmail.to(List.of(new SendSmtpEmailTo().email(email)));
        sendSmtpEmail.params(params);
        apiInstance.sendTransacEmail(sendSmtpEmail);
    }

    private void setDefaultClient() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(brevoApiKey);
    }

    public void sendPasswordChangeEmail(TentativeChangementMdp tentativeChangementMdp, String email) throws ApiException {
        setDefaultClient();
        var params = new HashMap<String, String>();
        params.put("lien", tentativeChangementMdp.getLink().toString());
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        sendSmtpEmail.templateId(5L);
        sendEmail(sendSmtpEmail, email, params);
    }

    public void sendInvitation(SimpleInvitationDto invitationDto) throws ApiException {
        setDefaultClient();
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        sendSmtpEmail.templateId(6L);
        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
        sendSmtpEmail.to(List.of(new SendSmtpEmailTo().email(invitationDto.getEmail())));
        apiInstance.sendTransacEmail(sendSmtpEmail);
    }

    /**
     * Envoie un email à l'administrateur d'un groupe pour lui dire que l'on souhaite
     * rejoindre le groupe
     * @param senderEmail l'email de l'utilisateur qui fait la demande
     * @param recipientEmail l'email de l'administrateur du groupe
     * @throws ApiException
     */
    public void sendDemandeAppartenance(String senderEmail, String recipientEmail) throws ApiException {
        setDefaultClient();
        var params = new HashMap<String, String>();
        params.put("mail", senderEmail);
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        sendSmtpEmail.templateId(6L);
        sendEmail(sendSmtpEmail, recipientEmail, params);
    }
}
