package com.maxi.test.services;

import com.maxi.test.components.XmlFileNameFilter;
import com.maxi.test.components.XmlLoader;
import com.maxi.test.components.XmlProcessor;
import com.maxi.test.dao.check.Check;
import com.maxi.test.dao.check.CheckService;
import com.maxi.test.dao.salelines.SaleLine;
import com.maxi.test.dao.salelines.SaleLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("xmlService")
@Scope("prototype")
public class XmlToDatabaseServiceImpl implements XmlToDatabaseService {


    private final CheckService checkService;

    private final SaleLineService saleLineService;

    private final ApplicationContext context;

    private final XmlFileNameFilter xmlFileNameFilter;

    @Value("${path_in}")
    private File pathIn;

    @Value("${path_out:}")
    private File pathOut;

    @Value("${path_buf:}")
    private File pathBuf;

    @Value("${loc.hibernate.batch-size:1}")
    private Integer batchSize;

    public XmlToDatabaseServiceImpl(CheckService checkService,
                                    SaleLineService saleLineService, ApplicationContext context, XmlFileNameFilter xmlFileNameFilter) {
        this.checkService = checkService;
        this.saleLineService = saleLineService;
        this.context = context;
        this.xmlFileNameFilter = xmlFileNameFilter;
    }

    private File[] getFiles() {
        return pathIn.listFiles(this.xmlFileNameFilter);
    }

    private Path move(File in, File out) {
        try {
            Path file_out = Paths.get(out.getPath(),
                    new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date())
                    + in.getName());
            return Files.move(in.toPath(), file_out);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось перенести " + in + " в " + out);
        }
    }

    private void delete(File file) {
        if (!file.delete()) {
            throw new RuntimeException("Не удалось удалить файл " + file.getName());
        }
    }

    public void execute() {
        File[] files = getFiles();
        if (files == null) return;
        for (File file : files) {
            File bufPath = move(file, pathBuf).toFile();
            try {
                System.out.println("Начинаю парсинг " + file.getName() + " Время начала " + new Date());
                XmlLoader.load(
                        bufPath,
                        (XmlProcessor) context.getBean("xmlSaver"),
                        batchSize
                );
                move(bufPath, pathOut);
                System.out.println("Заканчиваю парсинг " + file.getName() + " Время окончания " + new Date());
            }
            catch (FileNotFoundException ignored) {}
            catch (Exception e) {
                writeLog(pathIn, file.getName(), e.getCause().toString());
                System.out.println("Заканчиваю парсинг " + file.getName() + " c ошибкой. Время окончания " + new Date());
            }
        }
    }

    public void writeLog(File path, String fileName, String log) {
        try {
            Path finalPath = Paths.get(path.toString(),
                    "123",
                            fileName
                            + "-"
                            + new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date())
                            + ".log");
            FileWriter writer = new FileWriter(finalPath.toFile());
            writer.write(log);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean("xmlSaver")
    public XmlProcessor getProcessor() {
        return new XmlProcessor() {
            @Override
            public void process(Check check, Iterable<SaleLine> lines) {
                saleLineService.saveAll(lines);
            }
        };
    }

}
