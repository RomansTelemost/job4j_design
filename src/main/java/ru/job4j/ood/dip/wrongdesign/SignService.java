package ru.job4j.ood.dip.wrongdesign;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * - Ошибка в типе данных поля LOG класса SignService. Должно быть либо интерфейс либо абстрактный класс.
 * В данном случае подойдет OutputStream.
 * - Ошибка в возвращаемом типе метода signDocuments и в передаваемом параметре(documents) в этом же методе
 * нужно сделать абстрактный класс Data. Document унаследовать от класса Data.(Image можно создать
 * и так же унаследовать от Data). Так же сделать DataStore(абстрактные методы add, get, getAll) интерфейс.
 * И Создать класс DocumentStore(ImageStore) реализующий интерфейс DataStore
 * В классе signDocuments что в параметре метода, что в возвращаемом типе указать
 * "public DataStore signDocuments(Signer signer, DataStore documents)"
 */
public class SignService {

    private static final PrintStream LOG = System.out;

    public SignService() throws Exception {
        LOG.write("create SignService object".getBytes());
    }

    /**
     * Возвращаем только подписанные документы
     * @param signer
     * @param documents
     * @return
     */
    public List<Document> signDocuments(Signer signer, List<Document> documents) {
        return documents.stream().
                filter(document -> document.getSigner() == null)
                .peek(document -> document.setSigner(signer))
                .collect(Collectors.toList());
    }
}
