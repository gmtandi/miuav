package com.amap.api.services.core;

import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.geocoder.BusinessArea;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.Cinema;
import com.amap.api.services.poisearch.Dining;
import com.amap.api.services.poisearch.Discount;
import com.amap.api.services.poisearch.Groupbuy;
import com.amap.api.services.poisearch.Hotel;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiItemDetail.DeepType;
import com.amap.api.services.poisearch.Scenic;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.District;
import com.amap.api.services.route.Doorway;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.RouteBusWalkItem;
import com.amap.api.services.route.RouteSearchCity;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.fimi.soul.entity.User;
import com.fimi.soul.utils.bq;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.AuthActivity;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.services.core.j */
public class C0477j {
    public static Scenic m4780a(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) {
        Scenic scenic = new Scenic();
        scenic.setIntro(C0477j.m4794b(jSONObject, "intro"));
        scenic.setRating(C0477j.m4794b(jSONObject, "rating"));
        scenic.setDeepsrc(C0477j.m4794b(jSONObject, "deepsrc"));
        scenic.setLevel(C0477j.m4794b(jSONObject, "level"));
        scenic.setPrice(C0477j.m4794b(jSONObject, "price"));
        scenic.setSeason(C0477j.m4794b(jSONObject, "season"));
        scenic.setRecommend(C0477j.m4794b(jSONObject, "recommend"));
        scenic.setTheme(C0477j.m4794b(jSONObject, "theme"));
        scenic.setOrderWapUrl(C0477j.m4794b(jSONObject, "ordering_wap_url"));
        scenic.setOrderWebUrl(C0477j.m4794b(jSONObject, "ordering_web_url"));
        scenic.setOpentimeGDF(C0477j.m4794b(jSONObject, "opentime_GDF"));
        scenic.setOpentime(C0477j.m4794b(jSONObject, "opentime"));
        scenic.setPhotos(C0477j.m4823l(jSONObject));
        poiItemDetail.setDeepType(DeepType.SCENIC);
        poiItemDetail.setScenic(scenic);
        return scenic;
    }

    public static ArrayList<SuggestionCity> m4781a(JSONObject jSONObject) {
        ArrayList<SuggestionCity> arrayList = new ArrayList();
        if (!jSONObject.has("cities")) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("cities");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new SuggestionCity(C0477j.m4794b(optJSONObject, User.FN_NAME), C0477j.m4794b(optJSONObject, "citycode"), C0477j.m4794b(optJSONObject, "adcode"), C0477j.m4817i(C0477j.m4794b(optJSONObject, "num"))));
            }
        }
        return arrayList;
    }

    public static List<BusPath> m4782a(JSONArray jSONArray) {
        List<BusPath> arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            BusPath busPath = new BusPath();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                busPath.setCost(C0477j.m4819j(C0477j.m4794b(optJSONObject, "cost")));
                busPath.setDuration(C0477j.m4821k(C0477j.m4794b(optJSONObject, "duration")));
                busPath.setNightBus(C0477j.m4824l(C0477j.m4794b(optJSONObject, "nightflag")));
                busPath.setWalkDistance(C0477j.m4819j(C0477j.m4794b(optJSONObject, "walking_distance")));
                JSONArray optJSONArray = optJSONObject.optJSONArray("segments");
                if (optJSONArray != null) {
                    List arrayList2 = new ArrayList();
                    float f = 0.0f;
                    float f2 = 0.0f;
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        if (optJSONObject2 != null) {
                            BusStep q = C0477j.m4829q(optJSONObject2);
                            if (q != null) {
                                arrayList2.add(q);
                                if (q.getWalk() != null) {
                                    f += q.getWalk().getDistance();
                                }
                                if (q.getBusLine() != null) {
                                    f2 += q.getBusLine().getDistance();
                                }
                            }
                        }
                    }
                    busPath.setSteps(arrayList2);
                    busPath.setBusDistance(f2);
                    busPath.setWalkDistance(f);
                    arrayList.add(busPath);
                }
            }
        }
        return arrayList;
    }

    public static void m4783a(Discount discount, JSONObject jSONObject) {
        discount.initPhotos(C0477j.m4823l(jSONObject));
    }

    public static void m4784a(Groupbuy groupbuy, JSONObject jSONObject) {
        groupbuy.initPhotos(C0477j.m4823l(jSONObject));
    }

    public static void m4785a(PoiItemDetail poiItemDetail, JSONObject jSONObject) {
        if (jSONObject != null) {
            if (poiItemDetail.isGroupbuyInfo()) {
                C0477j.m4796b(poiItemDetail, jSONObject);
            }
            if (poiItemDetail.isDiscountInfo()) {
                C0477j.m4802c(poiItemDetail, jSONObject);
            }
        }
    }

    public static void m4786a(DriveStep driveStep, JSONObject jSONObject) {
        try {
            List arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("cities");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    RouteSearchCity routeSearchCity = new RouteSearchCity();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        routeSearchCity.setSearchCityName(C0477j.m4794b(optJSONObject, User.FN_NAME));
                        routeSearchCity.setSearchCitycode(C0477j.m4794b(optJSONObject, "citycode"));
                        routeSearchCity.setSearchCityhAdCode(C0477j.m4794b(optJSONObject, "adcode"));
                        C0477j.m4787a(routeSearchCity, optJSONObject);
                        arrayList.add(routeSearchCity);
                    }
                }
                driveStep.setRouteSearchCityList(arrayList);
            }
        } catch (Throwable e) {
            C0471d.m4762a(e, "JSONHelper", "parseCrossCity");
        }
    }

    public static void m4787a(RouteSearchCity routeSearchCity, JSONObject jSONObject) {
        if (jSONObject.has("districts")) {
            try {
                List arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("districts");
                if (optJSONArray == null) {
                    routeSearchCity.setDistricts(arrayList);
                    return;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    District district = new District();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        district.setDistrictName(C0477j.m4794b(optJSONObject, User.FN_NAME));
                        district.setDistrictAdcode(C0477j.m4794b(optJSONObject, "adcode"));
                        arrayList.add(district);
                    }
                }
                routeSearchCity.setDistricts(arrayList);
            } catch (Throwable e) {
                C0471d.m4762a(e, "JSONHelper", "parseCrossDistricts");
            }
        }
    }

    public static void m4788a(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Crossroad crossroad = new Crossroad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                crossroad.setId(C0477j.m4794b(optJSONObject, LocaleUtil.INDONESIAN));
                crossroad.setDirection(C0477j.m4794b(optJSONObject, "direction"));
                crossroad.setDistance(C0477j.m4819j(C0477j.m4794b(optJSONObject, "distance")));
                crossroad.setCenterPoint(C0477j.m4799c(optJSONObject, "location"));
                crossroad.setFirstRoadId(C0477j.m4794b(optJSONObject, "first_id"));
                crossroad.setFirstRoadName(C0477j.m4794b(optJSONObject, "first_name"));
                crossroad.setSecondRoadId(C0477j.m4794b(optJSONObject, "second_id"));
                crossroad.setSecondRoadName(C0477j.m4794b(optJSONObject, "second_name"));
                arrayList.add(crossroad);
            }
        }
        regeocodeAddress.setCrossroads(arrayList);
    }

    public static void m4789a(JSONArray jSONArray, ArrayList<DistrictItem> arrayList, DistrictItem districtItem) {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(C0477j.m4825m(optJSONObject));
                }
            }
            if (districtItem != null) {
                districtItem.setSubDistrict(arrayList);
            }
        }
    }

    public static void m4790a(JSONObject jSONObject, RegeocodeAddress regeocodeAddress) {
        regeocodeAddress.setProvince(C0477j.m4794b(jSONObject, DistrictSearchQuery.KEYWORDS_PROVINCE));
        regeocodeAddress.setCity(C0477j.m4794b(jSONObject, DistrictSearchQuery.KEYWORDS_CITY));
        regeocodeAddress.setCityCode(C0477j.m4794b(jSONObject, "citycode"));
        regeocodeAddress.setAdCode(C0477j.m4794b(jSONObject, "adcode"));
        regeocodeAddress.setDistrict(C0477j.m4794b(jSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT));
        regeocodeAddress.setTownship(C0477j.m4794b(jSONObject, "township"));
        regeocodeAddress.setNeighborhood(C0477j.m4794b(jSONObject.optJSONObject("neighborhood"), User.FN_NAME));
        regeocodeAddress.setBuilding(C0477j.m4794b(jSONObject.optJSONObject("building"), User.FN_NAME));
        StreetNumber streetNumber = new StreetNumber();
        JSONObject optJSONObject = jSONObject.optJSONObject("streetNumber");
        streetNumber.setStreet(C0477j.m4794b(optJSONObject, "street"));
        streetNumber.setNumber(C0477j.m4794b(optJSONObject, "number"));
        streetNumber.setLatLonPoint(C0477j.m4799c(optJSONObject, "location"));
        streetNumber.setDirection(C0477j.m4794b(optJSONObject, "direction"));
        streetNumber.setDistance(C0477j.m4819j(C0477j.m4794b(optJSONObject, "distance")));
        regeocodeAddress.setStreetNumber(streetNumber);
        regeocodeAddress.setBusinessAreas(C0477j.m4828p(jSONObject));
    }

    public static boolean m4791a(String str) {
        try {
            if (str.equals(C2915a.f14760f)) {
                return false;
            }
            int parseInt = Integer.parseInt(str);
            return parseInt != 0 && parseInt == 1;
        } catch (Throwable e) {
            C0471d.m4762a(e, "JSONHelper", "ordingStr2Boolean");
            return false;
        } catch (Throwable e2) {
            C0471d.m4762a(e2, "JSONHelper", "ordingStr2BooleanException");
            return false;
        }
    }

    public static boolean m4792a(JSONObject jSONObject, String str) {
        return C0477j.m4791a(C0477j.m4794b(jSONObject.optJSONObject("biz_ext"), str));
    }

    public static BusRouteResult m4793b(String str) {
        BusRouteResult busRouteResult = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(bq.f10107a)) {
                busRouteResult = new BusRouteResult();
                jSONObject = jSONObject.optJSONObject(bq.f10107a);
                if (jSONObject != null) {
                    busRouteResult.setStartPos(C0477j.m4799c(jSONObject, "origin"));
                    busRouteResult.setTargetPos(C0477j.m4799c(jSONObject, "destination"));
                    busRouteResult.setTaxiCost(C0477j.m4819j(C0477j.m4794b(jSONObject, "taxi_cost")));
                    if (jSONObject.has("transits")) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("transits");
                        if (optJSONArray != null) {
                            busRouteResult.setPaths(C0477j.m4782a(optJSONArray));
                        }
                    }
                }
            }
            return busRouteResult;
        } catch (JSONException e) {
            throw new AMapException(AMapException.ERROR_PROTOCOL);
        }
    }

    public static String m4794b(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return C2915a.f14760f;
        }
        String str2 = C2915a.f14760f;
        return (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) ? str2 : jSONObject.optString(str);
    }

    public static ArrayList<String> m4795b(JSONObject jSONObject) {
        ArrayList<String> arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("keywords");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.optString(i));
        }
        return arrayList;
    }

    public static void m4796b(PoiItemDetail poiItemDetail, JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("groupbuys");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        Groupbuy groupbuy = new Groupbuy();
                        groupbuy.setTypeCode(C0477j.m4794b(optJSONObject, "typecode"));
                        groupbuy.setTypeDes(C0477j.m4794b(optJSONObject, SocialConstants.PARAM_TYPE));
                        groupbuy.setDetail(C0477j.m4794b(optJSONObject, "detail"));
                        groupbuy.setStartTime(C0471d.m4765c(C0477j.m4794b(optJSONObject, "start_time")));
                        groupbuy.setEndTime(C0471d.m4765c(C0477j.m4794b(optJSONObject, "end_time")));
                        groupbuy.setCount(C0477j.m4817i(C0477j.m4794b(optJSONObject, "num")));
                        groupbuy.setSoldCount(C0477j.m4817i(C0477j.m4794b(optJSONObject, "sold_num")));
                        groupbuy.setOriginalPrice(C0477j.m4819j(C0477j.m4794b(optJSONObject, "original_price")));
                        groupbuy.setGroupbuyPrice(C0477j.m4819j(C0477j.m4794b(optJSONObject, "groupbuy_price")));
                        groupbuy.setDiscount(C0477j.m4819j(C0477j.m4794b(optJSONObject, "discount")));
                        groupbuy.setTicketAddress(C0477j.m4794b(optJSONObject, "ticket_address"));
                        groupbuy.setTicketTel(C0477j.m4794b(optJSONObject, "ticket_tel"));
                        groupbuy.setUrl(C0477j.m4794b(optJSONObject, SocialConstants.PARAM_URL));
                        groupbuy.setProvider(C0477j.m4794b(optJSONObject, "provider"));
                        C0477j.m4784a(groupbuy, optJSONObject);
                        poiItemDetail.addGroupbuy(groupbuy);
                    }
                }
            }
        }
    }

    public static void m4797b(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) {
        Cinema cinema = new Cinema();
        cinema.setIntro(C0477j.m4794b(jSONObject, "intro"));
        cinema.setRating(C0477j.m4794b(jSONObject, "rating"));
        cinema.setDeepsrc(C0477j.m4794b(jSONObject, "deepsrc"));
        cinema.setParking(C0477j.m4794b(jSONObject, "parking"));
        cinema.setOpentimeGDF(C0477j.m4794b(jSONObject, "opentime_GDF"));
        cinema.setOpentime(C0477j.m4794b(jSONObject, "opentime"));
        cinema.setPhotos(C0477j.m4823l(jSONObject));
        if (C0477j.m4822k(jSONObject2)) {
            cinema.setSeatOrdering(C0477j.m4792a(jSONObject2, "seat_ordering"));
        }
        poiItemDetail.setDeepType(DeepType.CINEMA);
        poiItemDetail.setCinema(cinema);
    }

    public static void m4798b(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            RegeocodeRoad regeocodeRoad = new RegeocodeRoad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                regeocodeRoad.setId(C0477j.m4794b(optJSONObject, LocaleUtil.INDONESIAN));
                regeocodeRoad.setName(C0477j.m4794b(optJSONObject, User.FN_NAME));
                regeocodeRoad.setLatLngPoint(C0477j.m4799c(optJSONObject, "location"));
                regeocodeRoad.setDirection(C0477j.m4794b(optJSONObject, "direction"));
                regeocodeRoad.setDistance(C0477j.m4819j(C0477j.m4794b(optJSONObject, "distance")));
                arrayList.add(regeocodeRoad);
            }
        }
        regeocodeAddress.setRoads(arrayList);
    }

    public static LatLonPoint m4799c(JSONObject jSONObject, String str) {
        return (jSONObject != null && jSONObject.has(str)) ? C0477j.m4812f(jSONObject.optString(str)) : null;
    }

    public static DriveRouteResult m4800c(String str) {
        DriveRouteResult driveRouteResult = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(bq.f10107a)) {
                driveRouteResult = new DriveRouteResult();
                jSONObject = jSONObject.optJSONObject(bq.f10107a);
                if (jSONObject != null) {
                    driveRouteResult.setStartPos(C0477j.m4799c(jSONObject, "origin"));
                    driveRouteResult.setTargetPos(C0477j.m4799c(jSONObject, "destination"));
                    driveRouteResult.setTaxiCost(C0477j.m4819j(C0477j.m4794b(jSONObject, "taxi_cost")));
                    if (jSONObject.has("paths")) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("paths");
                        if (optJSONArray != null) {
                            List arrayList = new ArrayList();
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                DrivePath drivePath = new DrivePath();
                                jSONObject = optJSONArray.optJSONObject(i);
                                if (jSONObject != null) {
                                    drivePath.setDistance(C0477j.m4819j(C0477j.m4794b(jSONObject, "distance")));
                                    drivePath.setDuration(C0477j.m4821k(C0477j.m4794b(jSONObject, "duration")));
                                    drivePath.setStrategy(C0477j.m4794b(jSONObject, "strategy"));
                                    drivePath.setTolls(C0477j.m4819j(C0477j.m4794b(jSONObject, "tolls")));
                                    drivePath.setTollDistance(C0477j.m4819j(C0477j.m4794b(jSONObject, "toll_distance")));
                                    JSONArray optJSONArray2 = jSONObject.optJSONArray("steps");
                                    if (optJSONArray2 != null) {
                                        List arrayList2 = new ArrayList();
                                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                            DriveStep driveStep = new DriveStep();
                                            JSONObject optJSONObject = optJSONArray2.optJSONObject(i2);
                                            if (optJSONObject != null) {
                                                driveStep.setInstruction(C0477j.m4794b(optJSONObject, "instruction"));
                                                driveStep.setOrientation(C0477j.m4794b(optJSONObject, "orientation"));
                                                driveStep.setRoad(C0477j.m4794b(optJSONObject, "road"));
                                                driveStep.setDistance(C0477j.m4819j(C0477j.m4794b(optJSONObject, "distance")));
                                                driveStep.setTolls(C0477j.m4819j(C0477j.m4794b(optJSONObject, "tolls")));
                                                driveStep.setTollDistance(C0477j.m4819j(C0477j.m4794b(optJSONObject, "toll_distance")));
                                                driveStep.setTollRoad(C0477j.m4794b(optJSONObject, "toll_road"));
                                                driveStep.setDuration(C0477j.m4819j(C0477j.m4794b(optJSONObject, "duration")));
                                                driveStep.setPolyline(C0477j.m4806d(optJSONObject, "polyline"));
                                                driveStep.setAction(C0477j.m4794b(optJSONObject, AuthActivity.ACTION_KEY));
                                                driveStep.setAssistantAction(C0477j.m4794b(optJSONObject, "assistant_action"));
                                                C0477j.m4786a(driveStep, optJSONObject);
                                                arrayList2.add(driveStep);
                                            }
                                        }
                                        drivePath.setSteps(arrayList2);
                                        arrayList.add(drivePath);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            driveRouteResult.setPaths(arrayList);
                        }
                    }
                }
            }
            return driveRouteResult;
        } catch (Throwable e) {
            C0471d.m4762a(e, "JSONHelper", "parseDriveRoute");
            throw new AMapException(AMapException.ERROR_PROTOCOL);
        } catch (Throwable e2) {
            C0471d.m4762a(e2, "JSONHelper", "parseDriveRouteThrowable");
            AMapException aMapException = new AMapException(AMapException.ERROR_PROTOCOL);
        }
    }

    public static ArrayList<PoiItem> m4801c(JSONObject jSONObject) {
        ArrayList<PoiItem> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("pois");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(C0477j.m4804d(optJSONObject));
            }
        }
        return arrayList;
    }

    public static void m4802c(PoiItemDetail poiItemDetail, JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("discounts");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    Discount discount = new Discount();
                    discount.setTitle(C0477j.m4794b(optJSONObject, SocialConstants.PARAM_TITLE));
                    discount.setDetail(C0477j.m4794b(optJSONObject, "detail"));
                    discount.setStartTime(C0471d.m4765c(C0477j.m4794b(optJSONObject, "start_time")));
                    discount.setEndTime(C0471d.m4765c(C0477j.m4794b(optJSONObject, "end_time")));
                    discount.setSoldCount(C0477j.m4817i(C0477j.m4794b(optJSONObject, "sold_num")));
                    discount.setUrl(C0477j.m4794b(optJSONObject, SocialConstants.PARAM_URL));
                    discount.setProvider(C0477j.m4794b(optJSONObject, "provider"));
                    C0477j.m4783a(discount, optJSONObject);
                    poiItemDetail.addDiscount(discount);
                }
            }
        }
    }

    public static void m4803c(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) {
        Hotel hotel = new Hotel();
        hotel.setStar(C0477j.m4794b(jSONObject, "star"));
        hotel.setIntro(C0477j.m4794b(jSONObject, "intro"));
        hotel.setRating(C0477j.m4794b(jSONObject, "rating"));
        hotel.setLowestPrice(C0477j.m4794b(jSONObject, "lowest_price"));
        hotel.setDeepsrc(C0477j.m4794b(jSONObject, "deepsrc"));
        hotel.setFaciRating(C0477j.m4794b(jSONObject, "faci_rating"));
        hotel.setHealthRating(C0477j.m4794b(jSONObject, "health_rating"));
        hotel.setEnvironmentRating(C0477j.m4794b(jSONObject, "environment_rating"));
        hotel.setServiceRating(C0477j.m4794b(jSONObject, "service_rating"));
        hotel.setTraffic(C0477j.m4794b(jSONObject, "traffic"));
        hotel.setAddition(C0477j.m4794b(jSONObject, "addition"));
        hotel.setPhotos(C0477j.m4823l(jSONObject));
        poiItemDetail.setDeepType(DeepType.HOTEL);
        poiItemDetail.setHotel(hotel);
    }

    public static PoiItemDetail m4804d(JSONObject jSONObject) {
        PoiItemDetail poiItemDetail = new PoiItemDetail(C0477j.m4794b(jSONObject, LocaleUtil.INDONESIAN), C0477j.m4799c(jSONObject, "location"), C0477j.m4794b(jSONObject, User.FN_NAME), C0477j.m4794b(jSONObject, "address"));
        poiItemDetail.setAdCode(C0477j.m4794b(jSONObject, "adcode"));
        poiItemDetail.setProvinceName(C0477j.m4794b(jSONObject, "pname"));
        poiItemDetail.setCityName(C0477j.m4794b(jSONObject, "cityname"));
        poiItemDetail.setAdName(C0477j.m4794b(jSONObject, "adname"));
        poiItemDetail.setCityCode(C0477j.m4794b(jSONObject, "citycode"));
        poiItemDetail.setProvinceCode(C0477j.m4794b(jSONObject, "pcode"));
        poiItemDetail.setDirection(C0477j.m4794b(jSONObject, "direction"));
        if (jSONObject.has("distance")) {
            String b = C0477j.m4794b(jSONObject, "distance");
            if (!C0477j.m4816h(b)) {
                try {
                    poiItemDetail.setDistance((int) Float.parseFloat(b));
                } catch (Throwable e) {
                    C0471d.m4762a(e, "JSONHelper", "parseBasePoi");
                } catch (Throwable e2) {
                    C0471d.m4762a(e2, "JSONHelper", "parseBasePoi");
                }
                if (poiItemDetail.getDistance() == 0) {
                    poiItemDetail.setDistance(-1);
                }
            }
        }
        poiItemDetail.setTel(C0477j.m4794b(jSONObject, "tel"));
        poiItemDetail.setTypeDes(C0477j.m4794b(jSONObject, SocialConstants.PARAM_TYPE));
        poiItemDetail.setEnter(C0477j.m4799c(jSONObject, "entr_location"));
        poiItemDetail.setExit(C0477j.m4799c(jSONObject, "exit_location"));
        poiItemDetail.setWebsite(C0477j.m4794b(jSONObject, "website"));
        poiItemDetail.setPostcode(C0477j.m4794b(jSONObject, "citycode"));
        poiItemDetail.setEmail(C0477j.m4794b(jSONObject, "email"));
        if (C0477j.m4814g(C0477j.m4794b(jSONObject, "groupbuy_num"))) {
            poiItemDetail.setGroupbuyInfo(false);
        } else {
            poiItemDetail.setGroupbuyInfo(true);
        }
        if (C0477j.m4814g(C0477j.m4794b(jSONObject, "discount_num"))) {
            poiItemDetail.setDiscountInfo(false);
        } else {
            poiItemDetail.setDiscountInfo(true);
        }
        if (C0477j.m4814g(C0477j.m4794b(jSONObject, "indoor_map"))) {
            poiItemDetail.setIndoorMap(false);
        } else {
            poiItemDetail.setIndoorMap(true);
        }
        return poiItemDetail;
    }

    public static WalkRouteResult m4805d(String str) {
        Throwable e;
        WalkRouteResult walkRouteResult;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(bq.f10107a)) {
                return null;
            }
            walkRouteResult = new WalkRouteResult();
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject(bq.f10107a);
                walkRouteResult.setStartPos(C0477j.m4799c(optJSONObject, "origin"));
                walkRouteResult.setTargetPos(C0477j.m4799c(optJSONObject, "destination"));
                if (!optJSONObject.has("paths")) {
                    return walkRouteResult;
                }
                List arrayList = new ArrayList();
                JSONArray optJSONArray = optJSONObject.optJSONArray("paths");
                if (optJSONArray == null) {
                    walkRouteResult.setPaths(arrayList);
                    return walkRouteResult;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    WalkPath walkPath = new WalkPath();
                    optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        walkPath.setDistance(C0477j.m4819j(C0477j.m4794b(optJSONObject, "distance")));
                        walkPath.setDuration(C0477j.m4821k(C0477j.m4794b(optJSONObject, "duration")));
                        if (optJSONObject.has("steps")) {
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("steps");
                            List arrayList2 = new ArrayList();
                            if (optJSONArray2 != null) {
                                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    WalkStep walkStep = new WalkStep();
                                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                                    if (optJSONObject2 != null) {
                                        walkStep.setInstruction(C0477j.m4794b(optJSONObject2, "instruction"));
                                        walkStep.setOrientation(C0477j.m4794b(optJSONObject2, "orientation"));
                                        walkStep.setRoad(C0477j.m4794b(optJSONObject2, "road"));
                                        walkStep.setDistance(C0477j.m4819j(C0477j.m4794b(optJSONObject2, "distance")));
                                        walkStep.setDuration(C0477j.m4819j(C0477j.m4794b(optJSONObject2, "duration")));
                                        walkStep.setPolyline(C0477j.m4806d(optJSONObject2, "polyline"));
                                        walkStep.setAction(C0477j.m4794b(optJSONObject2, AuthActivity.ACTION_KEY));
                                        walkStep.setAssistantAction(C0477j.m4794b(optJSONObject2, "assistant_action"));
                                        arrayList2.add(walkStep);
                                    }
                                }
                                walkPath.setSteps(arrayList2);
                            }
                        }
                        arrayList.add(walkPath);
                    }
                }
                walkRouteResult.setPaths(arrayList);
                return walkRouteResult;
            } catch (JSONException e2) {
                e = e2;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            walkRouteResult = null;
            e = th;
            C0471d.m4762a(e, "JSONHelper", "parseWalkRoute");
            return walkRouteResult;
        }
    }

    public static ArrayList<LatLonPoint> m4806d(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? C0477j.m4808e(jSONObject.getString(str)) : null;
    }

    public static void m4807d(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) {
        Dining dining = new Dining();
        dining.setCuisines(C0477j.m4794b(jSONObject, "cuisines"));
        dining.setTag(C0477j.m4794b(jSONObject, "tag"));
        dining.setIntro(C0477j.m4794b(jSONObject, "intro"));
        dining.setRating(C0477j.m4794b(jSONObject, "rating"));
        dining.setCpRating(C0477j.m4794b(jSONObject, "cp_rating"));
        dining.setDeepsrc(C0477j.m4794b(jSONObject, "deepsrc"));
        dining.setTasteRating(C0477j.m4794b(jSONObject, "taste_rating"));
        dining.setEnvironmentRating(C0477j.m4794b(jSONObject, "environment_rating"));
        dining.setServiceRating(C0477j.m4794b(jSONObject, "service_rating"));
        dining.setCost(C0477j.m4794b(jSONObject, "cost"));
        dining.setRecommend(C0477j.m4794b(jSONObject, "recommend"));
        dining.setAtmosphere(C0477j.m4794b(jSONObject, "atmosphere"));
        dining.setOrderingWapUrl(C0477j.m4794b(jSONObject, "ordering_wap_url"));
        dining.setOrderingWebUrl(C0477j.m4794b(jSONObject, "ordering_web_url"));
        dining.setOrderinAppUrl(C0477j.m4794b(jSONObject, "ordering_app_url"));
        dining.setOpentimeGDF(C0477j.m4794b(jSONObject, "opentime_GDF"));
        dining.setOpentime(C0477j.m4794b(jSONObject, "opentime"));
        dining.setAddition(C0477j.m4794b(jSONObject, "addition"));
        dining.setPhotos(C0477j.m4823l(jSONObject));
        if (C0477j.m4822k(jSONObject2)) {
            dining.setMealOrdering(C0477j.m4792a(jSONObject2, "meal_ordering"));
        }
        poiItemDetail.setDeepType(DeepType.DINING);
        poiItemDetail.setDining(dining);
    }

    public static ArrayList<LatLonPoint> m4808e(String str) {
        ArrayList<LatLonPoint> arrayList = new ArrayList();
        String[] split = str.split(";");
        for (String f : split) {
            arrayList.add(C0477j.m4812f(f));
        }
        return arrayList;
    }

    public static ArrayList<BusStationItem> m4809e(JSONObject jSONObject) {
        ArrayList<BusStationItem> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("busstops");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(C0477j.m4811f(optJSONObject));
            }
        }
        return arrayList;
    }

    public static void m4810e(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null) {
            String b = C0477j.m4794b(jSONObject, SocialConstants.PARAM_TYPE);
            if (b.equalsIgnoreCase("hotel")) {
                C0477j.m4803c(poiItemDetail, jSONObject, jSONObject2);
            }
            if (b.equalsIgnoreCase("dining")) {
                C0477j.m4807d(poiItemDetail, jSONObject, jSONObject2);
            }
            if (b.equalsIgnoreCase("cinema")) {
                C0477j.m4797b(poiItemDetail, jSONObject, jSONObject2);
            }
            if (b.equalsIgnoreCase("scenic")) {
                C0477j.m4780a(poiItemDetail, jSONObject, jSONObject2);
            }
        }
    }

    public static BusStationItem m4811f(JSONObject jSONObject) {
        BusStationItem g = C0477j.m4813g(jSONObject);
        if (g == null) {
            return g;
        }
        g.setAdCode(C0477j.m4794b(jSONObject, "adcode"));
        g.setCityCode(C0477j.m4794b(jSONObject, "citycode"));
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        List arrayList = new ArrayList();
        if (optJSONArray == null) {
            g.setBusLineItems(arrayList);
            return g;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(C0477j.m4815h(optJSONObject));
            }
        }
        g.setBusLineItems(arrayList);
        return g;
    }

    public static LatLonPoint m4812f(String str) {
        if (str == null || str.equals(C2915a.f14760f) || str.equals("[]")) {
            return null;
        }
        String[] split = str.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        if (split.length != 2) {
            return null;
        }
        return new LatLonPoint(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
    }

    public static BusStationItem m4813g(JSONObject jSONObject) {
        BusStationItem busStationItem = new BusStationItem();
        busStationItem.setBusStationId(C0477j.m4794b(jSONObject, LocaleUtil.INDONESIAN));
        busStationItem.setLatLonPoint(C0477j.m4799c(jSONObject, "location"));
        busStationItem.setBusStationName(C0477j.m4794b(jSONObject, User.FN_NAME));
        return busStationItem;
    }

    public static boolean m4814g(String str) {
        return str == null || str.equals(C2915a.f14760f) || str.equals(Constants.VIA_RESULT_SUCCESS);
    }

    public static BusLineItem m4815h(JSONObject jSONObject) {
        BusLineItem busLineItem = new BusLineItem();
        busLineItem.setBusLineId(C0477j.m4794b(jSONObject, LocaleUtil.INDONESIAN));
        busLineItem.setBusLineType(C0477j.m4794b(jSONObject, SocialConstants.PARAM_TYPE));
        busLineItem.setBusLineName(C0477j.m4794b(jSONObject, User.FN_NAME));
        busLineItem.setDirectionsCoordinates(C0477j.m4806d(jSONObject, "polyline"));
        busLineItem.setCityCode(C0477j.m4794b(jSONObject, "citycode"));
        busLineItem.setOriginatingStation(C0477j.m4794b(jSONObject, "start_stop"));
        busLineItem.setTerminalStation(C0477j.m4794b(jSONObject, "end_stop"));
        return busLineItem;
    }

    public static boolean m4816h(String str) {
        return str == null || str.equals(C2915a.f14760f);
    }

    public static int m4817i(String str) {
        int i = 0;
        if (!(str == null || str.equals(C2915a.f14760f) || str.equals("[]"))) {
            try {
                i = Integer.parseInt(str);
            } catch (Throwable e) {
                C0471d.m4762a(e, "JSONHelper", "str2int");
            }
        }
        return i;
    }

    public static ArrayList<BusLineItem> m4818i(JSONObject jSONObject) {
        ArrayList<BusLineItem> arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(C0477j.m4820j(optJSONObject));
            }
        }
        return arrayList;
    }

    public static float m4819j(String str) {
        float f = 0.0f;
        if (!(str == null || str.equals(C2915a.f14760f) || str.equals("[]"))) {
            try {
                f = Float.parseFloat(str);
            } catch (Throwable e) {
                C0471d.m4762a(e, "JSONHelper", "str2float");
            }
        }
        return f;
    }

    public static BusLineItem m4820j(JSONObject jSONObject) {
        BusLineItem h = C0477j.m4815h(jSONObject);
        if (h == null) {
            return h;
        }
        h.setFirstBusTime(C0471d.m4766d(C0477j.m4794b(jSONObject, "start_time")));
        h.setLastBusTime(C0471d.m4766d(C0477j.m4794b(jSONObject, "end_time")));
        h.setBusCompany(C0477j.m4794b(jSONObject, "company"));
        h.setDistance(C0477j.m4819j(C0477j.m4794b(jSONObject, "distance")));
        h.setBasicPrice(C0477j.m4819j(C0477j.m4794b(jSONObject, "basic_price")));
        h.setTotalPrice(C0477j.m4819j(C0477j.m4794b(jSONObject, "total_price")));
        h.setBounds(C0477j.m4806d(jSONObject, "bounds"));
        List arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("busstops");
        if (optJSONArray == null) {
            h.setBusStations(arrayList);
            return h;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(C0477j.m4813g(optJSONObject));
            }
        }
        h.setBusStations(arrayList);
        return h;
    }

    public static long m4821k(String str) {
        long j = 0;
        if (!(str == null || str.equals(C2915a.f14760f) || str.equals("[]"))) {
            try {
                j = Long.parseLong(str);
            } catch (Throwable e) {
                C0471d.m4762a(e, "JSONHelper", "str2long");
            }
        }
        return j;
    }

    public static boolean m4822k(JSONObject jSONObject) {
        return jSONObject != null && jSONObject.has("biz_ext");
    }

    public static List<Photo> m4823l(JSONObject jSONObject) {
        List arrayList = new ArrayList();
        if (jSONObject != null && jSONObject.has("photos")) {
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("photos");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    Photo photo = new Photo();
                    photo.setTitle(C0477j.m4794b(optJSONObject, SocialConstants.PARAM_TITLE));
                    photo.setUrl(C0477j.m4794b(optJSONObject, SocialConstants.PARAM_URL));
                    arrayList.add(photo);
                }
            } catch (Throwable e) {
                C0471d.m4762a(e, "JSONHelper", "getPhotoList");
            }
        }
        return arrayList;
    }

    public static boolean m4824l(String str) {
        return (str == null || str.equals(C2915a.f14760f) || str.equals("[]") || str.equals(Constants.VIA_RESULT_SUCCESS) || !str.equals(Constants.VIA_TO_TYPE_QQ_GROUP)) ? false : true;
    }

    public static DistrictItem m4825m(JSONObject jSONObject) {
        DistrictItem districtItem = new DistrictItem();
        districtItem.setCitycode(C0477j.m4794b(jSONObject, "citycode"));
        districtItem.setAdcode(C0477j.m4794b(jSONObject, "adcode"));
        districtItem.setName(C0477j.m4794b(jSONObject, User.FN_NAME));
        districtItem.setLevel(C0477j.m4794b(jSONObject, "level"));
        districtItem.setCenter(C0477j.m4799c(jSONObject, "center"));
        if (jSONObject.has("polyline")) {
            String string = jSONObject.getString("polyline");
            if (string != null && string.length() > 0) {
                districtItem.setDistrictBoundary(string.split("\\|"));
            }
        }
        C0477j.m4789a(jSONObject.optJSONArray("districts"), new ArrayList(), districtItem);
        return districtItem;
    }

    public static ArrayList<GeocodeAddress> m4826n(JSONObject jSONObject) {
        ArrayList<GeocodeAddress> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("geocodes");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                GeocodeAddress geocodeAddress = new GeocodeAddress();
                geocodeAddress.setFormatAddress(C0477j.m4794b(optJSONObject, "formatted_address"));
                geocodeAddress.setProvince(C0477j.m4794b(optJSONObject, DistrictSearchQuery.KEYWORDS_PROVINCE));
                geocodeAddress.setCity(C0477j.m4794b(optJSONObject, DistrictSearchQuery.KEYWORDS_CITY));
                geocodeAddress.setDistrict(C0477j.m4794b(optJSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT));
                geocodeAddress.setTownship(C0477j.m4794b(optJSONObject, "township"));
                geocodeAddress.setNeighborhood(C0477j.m4794b(optJSONObject.optJSONObject("neighborhood"), User.FN_NAME));
                geocodeAddress.setBuilding(C0477j.m4794b(optJSONObject.optJSONObject("building"), User.FN_NAME));
                geocodeAddress.setAdcode(C0477j.m4794b(optJSONObject, "adcode"));
                geocodeAddress.setLatLonPoint(C0477j.m4799c(optJSONObject, "location"));
                geocodeAddress.setLevel(C0477j.m4794b(optJSONObject, "level"));
                arrayList.add(geocodeAddress);
            }
        }
        return arrayList;
    }

    public static ArrayList<Tip> m4827o(JSONObject jSONObject) {
        ArrayList<Tip> arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("tips");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            Tip tip = new Tip();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                tip.setName(C0477j.m4794b(optJSONObject, User.FN_NAME));
                tip.setDistrict(C0477j.m4794b(optJSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT));
                tip.setAdcode(C0477j.m4794b(optJSONObject, "adcode"));
                arrayList.add(tip);
            }
        }
        return arrayList;
    }

    public static List<BusinessArea> m4828p(JSONObject jSONObject) {
        List<BusinessArea> arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("businessAreas");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            BusinessArea businessArea = new BusinessArea();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                businessArea.setCenterPoint(C0477j.m4799c(optJSONObject, "location"));
                businessArea.setName(C0477j.m4794b(optJSONObject, User.FN_NAME));
                arrayList.add(businessArea);
            }
        }
        return arrayList;
    }

    public static BusStep m4829q(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        BusStep busStep = new BusStep();
        JSONObject optJSONObject = jSONObject.optJSONObject("walking");
        if (optJSONObject != null) {
            busStep.setWalk(C0477j.m4830r(optJSONObject));
        }
        optJSONObject = jSONObject.optJSONObject("bus");
        if (optJSONObject != null) {
            busStep.setBusLines(C0477j.m4831s(optJSONObject));
        }
        optJSONObject = jSONObject.optJSONObject("entrance");
        if (optJSONObject != null) {
            busStep.setEntrance(C0477j.m4832t(optJSONObject));
        }
        optJSONObject = jSONObject.optJSONObject("exit");
        if (optJSONObject == null) {
            return busStep;
        }
        busStep.setExit(C0477j.m4832t(optJSONObject));
        return busStep;
    }

    public static RouteBusWalkItem m4830r(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        RouteBusWalkItem routeBusWalkItem = new RouteBusWalkItem();
        routeBusWalkItem.setOrigin(C0477j.m4799c(jSONObject, "origin"));
        routeBusWalkItem.setDestination(C0477j.m4799c(jSONObject, "destination"));
        routeBusWalkItem.setDistance(C0477j.m4819j(C0477j.m4794b(jSONObject, "distance")));
        routeBusWalkItem.setDuration(C0477j.m4821k(C0477j.m4794b(jSONObject, "duration")));
        if (!jSONObject.has("steps")) {
            return routeBusWalkItem;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("steps");
        if (optJSONArray == null) {
            return routeBusWalkItem;
        }
        List arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(C0477j.m4833u(optJSONObject));
            }
        }
        routeBusWalkItem.setSteps(arrayList);
        return routeBusWalkItem;
    }

    public static List<RouteBusLineItem> m4831s(JSONObject jSONObject) {
        List<RouteBusLineItem> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(C0477j.m4834v(optJSONObject));
            }
        }
        return arrayList;
    }

    public static Doorway m4832t(JSONObject jSONObject) {
        Doorway doorway = new Doorway();
        doorway.setName(C0477j.m4794b(jSONObject, User.FN_NAME));
        doorway.setLatLonPoint(C0477j.m4799c(jSONObject, "location"));
        return doorway;
    }

    public static WalkStep m4833u(JSONObject jSONObject) {
        WalkStep walkStep = new WalkStep();
        walkStep.setInstruction(C0477j.m4794b(jSONObject, "instruction"));
        walkStep.setOrientation(C0477j.m4794b(jSONObject, "orientation"));
        walkStep.setRoad(C0477j.m4794b(jSONObject, "road"));
        walkStep.setDistance(C0477j.m4819j(C0477j.m4794b(jSONObject, "distance")));
        walkStep.setDuration(C0477j.m4819j(C0477j.m4794b(jSONObject, "duration")));
        walkStep.setPolyline(C0477j.m4806d(jSONObject, "polyline"));
        walkStep.setAction(C0477j.m4794b(jSONObject, AuthActivity.ACTION_KEY));
        walkStep.setAssistantAction(C0477j.m4794b(jSONObject, "assistant_action"));
        return walkStep;
    }

    public static RouteBusLineItem m4834v(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        RouteBusLineItem routeBusLineItem = new RouteBusLineItem();
        routeBusLineItem.setDepartureBusStation(C0477j.m4836x(jSONObject.optJSONObject("departure_stop")));
        routeBusLineItem.setArrivalBusStation(C0477j.m4836x(jSONObject.optJSONObject("arrival_stop")));
        routeBusLineItem.setBusLineName(C0477j.m4794b(jSONObject, User.FN_NAME));
        routeBusLineItem.setBusLineId(C0477j.m4794b(jSONObject, LocaleUtil.INDONESIAN));
        routeBusLineItem.setBusLineType(C0477j.m4794b(jSONObject, SocialConstants.PARAM_TYPE));
        routeBusLineItem.setDistance(C0477j.m4819j(C0477j.m4794b(jSONObject, "distance")));
        routeBusLineItem.setDuration(C0477j.m4819j(C0477j.m4794b(jSONObject, "duration")));
        routeBusLineItem.setPolyline(C0477j.m4806d(jSONObject, "polyline"));
        routeBusLineItem.setFirstBusTime(C0471d.m4766d(C0477j.m4794b(jSONObject, "start_time")));
        routeBusLineItem.setLastBusTime(C0471d.m4766d(C0477j.m4794b(jSONObject, "end_time")));
        routeBusLineItem.setPassStationNum(C0477j.m4817i(C0477j.m4794b(jSONObject, "via_num")));
        routeBusLineItem.setPassStations(C0477j.m4835w(jSONObject));
        return routeBusLineItem;
    }

    public static List<BusStationItem> m4835w(JSONObject jSONObject) {
        List<BusStationItem> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("via_stops");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(C0477j.m4836x(optJSONObject));
            }
        }
        return arrayList;
    }

    public static BusStationItem m4836x(JSONObject jSONObject) {
        BusStationItem busStationItem = new BusStationItem();
        busStationItem.setBusStationName(C0477j.m4794b(jSONObject, User.FN_NAME));
        busStationItem.setBusStationId(C0477j.m4794b(jSONObject, LocaleUtil.INDONESIAN));
        busStationItem.setLatLonPoint(C0477j.m4799c(jSONObject, "location"));
        return busStationItem;
    }
}
