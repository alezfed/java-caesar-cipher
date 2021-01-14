package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Cipher;
import com.epam.izh.rd.online.entity.Text;
import com.epam.izh.rd.online.repository.RamTextRepository;
import com.epam.izh.rd.online.repository.TextRepository;

public class ProcessCryptoService {
    private TextRepository ramTextRepository;
    private CryptoService cryptoService;
    private NotificationService notificationService;
    private UserRequestService userRequestService;

    public ProcessCryptoService() {
        ramTextRepository = new RamTextRepository();
        cryptoService = new CryptoService();
        notificationService = new NotificationService();
        userRequestService = new UserRequestService();
    }

    public void transformAll(String content, int direct) {
        direct = (int) Math.signum(direct);
        Cipher cipher = new Cipher(0);
        Text text = new Text(content, cipher);
        for (int i = 1; i < cipher.getCapacity(); i++) {
            cipher.setKey(direct * i);
            text.setCipher(cipher);
            ramTextRepository.save(cryptoService.transformText(text));
        }
        notificationService.showMessage(NotificationService.DONE);
    }

    public void printAll(int maxLength) {
        if (ramTextRepository.count() == 0) {
            notificationService.showMessage(NotificationService.EMPTY);
            return;
        }
        if (maxLength < 5) {
            notificationService.showMessage(NotificationService.ERROR_PREVIEW);
            maxLength = 50;
        }
        notificationService.showMessage(NotificationService.IN_REPOSITORY);
        for (int i = 0; i < ramTextRepository.count(); i++) {
            Text text = ramTextRepository.read(i);
            notificationService.showMessage(NotificationService.PREVIEW, i + 1, text.getAfterContent()
                    .substring(0, Math.min(text.getAfterContent().length(), maxLength)));
        }
    }

    public void transformFromInput(int direct) {
        notificationService.showMessage(NotificationService.INPUT_TEXT);
        String userText = userRequestService.getText();
        transformAll(userText, direct);
    }

    public void clearAll() {
        ramTextRepository.clear();
        notificationService.showMessage(NotificationService.EMPTY);
    }

    public void printById() {
        if (ramTextRepository.count() == 0) {
            notificationService.showMessage(NotificationService.EMPTY);
            return;
        }
        while (true) {
            notificationService.showMessage(NotificationService.INPUT_ID);
            int id = userRequestService.getIntNumber() - 1;
            if (id >= 0 && id <= ramTextRepository.count()) {
                notificationService.showMessage(NotificationService.PRINT_ID
                        , ramTextRepository.read(id).getCipher().getKey(), "");
                notificationService.showMessage(NotificationService.PRINT_ID_BEFORE);
                notificationService.showMessage(ramTextRepository.read(id).getBeforeContent());
                notificationService.showMessage(NotificationService.PRINT_ID_AFTER);
                notificationService.showMessage(ramTextRepository.read(id).getAfterContent());
                return;
            }
            notificationService.showMessage(NotificationService.ERROR_CHOICE);
        }
    }

    public void startCryptoService() {
        notificationService.showMessage(NotificationService.START);
        while (true) {
            notificationService.showMessage(NotificationService.CHOICE);
            int userChoice = userRequestService.getIntNumber();
            if (userChoice < 0 || userChoice > 7) {
                userChoice = 8;
            }
            switch (userChoice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    transformAll(NotificationService.EXAMPLE, -1);
                    break;
                case 2:
                    transformAll(NotificationService.EXAMPLE, +1);
                    break;
                case 3:
                    transformFromInput(-1);
                    break;
                case 4:
                    transformFromInput(1);
                    break;
                case 5:
                    printAll(50);
                    break;
                case 6:
                    printById();
                    break;
                case 7:
                    clearAll();
                    break;
                case 8:
                    notificationService.showMessage(NotificationService.ERROR_CHOICE);
                    break;
                default:
                    notificationService.showMessage(NotificationService.ERROR_MENU);
                    System.exit(0);
                    break;
            }
        }
    }
}