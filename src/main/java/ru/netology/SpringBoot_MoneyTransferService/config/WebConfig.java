package ru.netology.SpringBoot_MoneyTransferService.config;
/*
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.SpringBoot_MoneyTransferService.model.TransferTransaction;
import ru.netology.SpringBoot_MoneyTransferService.model.TransferTransactionArgument;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CardArgumentResolver());
    }

    private static class CardArgumentResolver implements HandlerMethodArgumentResolver {
        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.hasParameterAnnotation(TransferTransactionArgument.class);
        }

        @Override
        public Object resolveArgument(MethodParameter parameter,
                                      ModelAndViewContainer mavContainer,
                                      NativeWebRequest webRequest,
                                      WebDataBinderFactory binderFactory) throws Exception {

            String cardFromNumber = webRequest.getParameter("cardFromNumber");
            String cardFromValidTill = webRequest.getParameter("cardFromValidTill");
            String cardFromCVV = webRequest.getParameter("cardFromCVV");
            String cardToNumber = webRequest.getParameter("cardToNumber");
            Integer amount = webRequest.getParameter("amount");

            return new TransferTransaction(cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, amount);
        }
    }
}*/