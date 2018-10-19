package org.ddrent.web.tradingArea;

import com.aifeng.ddrent.common.enums.data.DataSourceEnum;
import com.aifeng.ddrent.common.enums.tradingArea.TradingLevelEnum;
import com.aifeng.ddrent.common.util.data.id.SequenceGeneratorUtil;
import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aifeng.ddrent.core.dao.model.trad.TradingAreaDO;
import com.aifeng.ddrent.core.service.trad.TradingAreaService;
import com.aifeng.ddrent.web.DdrentApplication;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.ddrent.web.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes= DdrentApplication.class)
//@RunWith(Parameterized.class)
public class HizhuTradingArea extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(HizhuTradingArea.class);

    @Autowired
    private TradingAreaService tradingAreaService;

    public static final String REGION_ATTR = "data-region-id";

    public static final String CREATOR = "系统管理员";

    public static final long CREATOR_ID = 6450298812944100606L;

    // ok http client
    public static OkHttpClient client = new OkHttpClient();

    // document
    static DocumentBuilder db = null;

    static {
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static final String DOMAIN = "http://m.hizhu.com";

    // 一级区域url
    public static final String REGION_URL = "http://m.hizhu.com/hangzhou/region_html.html";

    @Test
    public void insertByBatch() {
        String[] regions = new String[]{"hangzhou_杭州市", "shanghai_上海市", "beijing_北京市", "shenzhen_深圳市", "nanjing_南京市"
                , "guangzhou_广州市", "zhengzhou_郑州市", "suzhou_苏州市", "wuhan_武汉市", "tianjin_天津市"};

        // 获取所有一级区域
        List<TradingAreaDO> tradingAreaDOS = getRegions(regions);

        // 获取所有区域商圈
        List<TradingAreaDO> records = new ArrayList<>(128);
        records.addAll(tradingAreaDOS);
        tradingAreaDOS.stream().map(HizhuTradingArea::getTradingArea).forEach(tradingAreaDOS1 -> {
            records.addAll(tradingAreaDOS1);
        });

        tradingAreaService.insertByBatch(records);
    }

    public static void main(String[] args) {
        String[] regions = new String[]{"hangzhou_杭州市", "shanghai_上海市", "beijing_北京市", "shenzhen_深圳市", "nanjing_南京市"
                , "guangzhou_广州市", "zhengzhou_郑州市", "suzhou_苏州市", "wuhan_武汉市", "tianjin_天津市"};

        List<TradingAreaDO> tradingAreaDOS = getRegions(regions);

//        TradingAreaDO tradingAreaDO = new TradingAreaDO();
//
//        tradingAreaDO.setSnapshot("");
//        tradingAreaDO.setStructure("");
//        tradingAreaDO.setLevel(2);
//        tradingAreaDO.setAddress("杭州市");
//        tradingAreaDO.setId(1231441L);
//        tradingAreaDO.setCity("hangzhou");
//        tradingAreaDO.setExtra1("4");
//        tradingAreaDO.setName("西湖");
//        tradingAreaDO.setCreatortId(CREATOR_ID);
//        tradingAreaDO.setCreator(CREATOR);
//        tradingAreaDO.setOrigin(DataSourceEnum.HIZHU.name());
//
//
//        List<TradingAreaDO> tradingAreaDOS = getTradingArea(tradingAreaDO);

        List<TradingAreaDO> newList = new ArrayList<>(128);
        newList.addAll(tradingAreaDOS);
        tradingAreaDOS.stream().map(HizhuTradingArea::getTradingArea).forEach(tradingAreaDOS1 -> {
            newList.addAll(tradingAreaDOS1);
        });

//        tradingAreaDOS.stream().map(TradingAreaDO::getExtra1).forEach(System.out::println);

        newList.forEach(System.out::println);

//        System.out.println(getRegions(regions));
    }

    public static void searchingEstate(String key){

    }


    /**
     * 获取区下面的商圈
     * @param tradingArea
     * @return
     */
    private static List<TradingAreaDO> getTradingArea(TradingAreaDO tradingArea) {

        List<TradingAreaDO> records = new ArrayList<>(128);

        String url = String.format("%s/%s/%s", DOMAIN, tradingArea.getCity(), "plate_html.html");
        Request request = new Request.Builder()
                .method("POST", new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/x-www-form-urlencoded");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {
                        String data = "region=" + tradingArea.getExtra1();
                        sink.write(data.getBytes());
                    }
                }).url(url).build();

        BufferedReader bufferedReader = null;
        try {
            // 发送url请求
            Reader reader = client.newCall(request).execute().body().charStream();

            // 读取所有文件内容
            bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<root>");
            String line = null;
            while (StringUtils.isNotBlank(line = bufferedReader.readLine())){
                stringBuilder.append(line);
            }

            //如果文件内容不为空，则转换为document 进行解析
            if(stringBuilder.length() > 6){
                stringBuilder.append("</root>");
                String content = stringBuilder.toString().trim();

                logger.info("发送url[{}]请求, 返回结果为：\n{}", url, content.substring(6, content.length()-6));

                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(content));
                Document document = db.parse(is);

                NodeList nodeList = document.getElementsByTagName("a");

                TradingAreaDO record = null;
                for(int i = 0; i < nodeList.getLength(); i++){
                    Node node = nodeList.item(i);

                    if(node.getTextContent().contains("不限")) continue;

                    record = new TradingAreaDO();
                    record.setName(node.getTextContent());
//                        record.setExtra1(node.getAttributes().getNamedItem(REGION_ATTR).getNodeValue());
                    record.setCity(tradingArea.getCity());
                    record.setCreator(CREATOR);
                    record.setCreatortId(CREATOR_ID);
                    record.setLevel(TradingLevelEnum.AREA.ordinal());
                    record.setId(SequenceGeneratorUtil.nextId());
                    record.setAddress(tradingArea.getAddress() + tradingArea.getName() + "区");
                    record.setOrigin(DataSourceEnum.HIZHU.name());
                    //设置为市级
                    record.setLevel(TradingLevelEnum.CITY.ordinal());
                    record.setStructure(tradingArea.getStructure() + "_" + tradingArea.getId());
                    record.setSnapshot(nodeToString(node));
                    records.add(record);
                }
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        } finally {
            if(null != bufferedReader){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return records;
    }

    /**
     * 获取市下面的区
     * @param regions
     * @return
     */
    private static List<TradingAreaDO> getRegions(String[] regions) {
        assert null != regions;

        List<TradingAreaDO> records = new ArrayList<>(128);

        String url = null;
        String regionCode = null;
        String regionName = null;
        String[] regionArr = null;
        for (String region: regions) {

            regionArr = region.split("_");

            // 拼装当前区域请求
            url = String.format("%s/%s/%s", DOMAIN, regionArr[0], "region_html.html");
            Request request = new Request.Builder()
                    .method("POST", new RequestBody() {
                        @Override
                        public MediaType contentType() {
                            return MediaType.parse("application/x-www-form-urlencoded");
                        }

                        @Override
                        public void writeTo(BufferedSink sink) throws IOException {
                        }
                    }).url(url).build();

            BufferedReader bufferedReader = null;
            try {
                // 发送url请求
                Reader reader = client.newCall(request).execute().body().charStream();

                // 读取所有文件内容
                bufferedReader = new BufferedReader(reader);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<root>");
                String line = null;
                while (StringUtils.isNotBlank(line = bufferedReader.readLine())){
                    stringBuilder.append(line);
                }

                //如果文件内容不为空，则转换为document 进行解析
                if(stringBuilder.length() > 6){
                    stringBuilder.append("</root>");
                    String content = stringBuilder.toString().trim();

                    logger.info("发送url[{}]请求, 返回结果为：\n{}", url, content.substring(6, content.length()-6));

                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(content));
                    Document document = db.parse(is);

                    NodeList nodeList = document.getElementsByTagName("a");

                    TradingAreaDO record = null;
                    for(int i = 0; i < nodeList.getLength(); i++){
                        Node node = nodeList.item(i);
                        if(null != node.getAttributes().getNamedItem(REGION_ATTR)){
                            record = new TradingAreaDO();
                            record.setName(node.getTextContent());
                            record.setExtra1(node.getAttributes().getNamedItem(REGION_ATTR).getNodeValue());
                            record.setCity(regionArr[0]);
                            record.setCreator(CREATOR);
                            record.setCreatortId(CREATOR_ID);
                            record.setId(SequenceGeneratorUtil.nextId());
                            record.setAddress(regionArr[1]);
                            record.setOrigin(DataSourceEnum.HIZHU.name());
                            //设置为市级
                            record.setLevel(TradingLevelEnum.CITY.ordinal());
                            record.setStructure("");
                            record.setSnapshot(nodeToString(node));
                            records.add(record);
                        }
                    }
                }
            } catch (IOException | SAXException e) {
                e.printStackTrace();
            } finally {
                if(null != bufferedReader){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return records;
    }

    private static String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            System.out.println("nodeToString Transformer Exception");
        }
        return sw.toString();
    }
}
