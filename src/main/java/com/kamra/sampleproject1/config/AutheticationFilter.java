package com.kamra.sampleproject1.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class AutheticationFilter extends OncePerRequestFilter {




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        System.out.println(header);

        if (header != null && header.startsWith("Basic ")) {
            String base64Token = header.substring(6);
            System.out.println(base64Token);

            String base64Credentials = header.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.decode(base64Credentials.getBytes());
            String token = new String(credDecoded);
            String username = "";
            String password = "";
            int delim = token.indexOf(":");
            if (delim != -1)
            {
                username = token.substring(0, delim);
                password = token.substring(delim + 1);
            }
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            final String[] values = credentials.split(":", 2);





            if(username.equals("user")){
                filterChain.doFilter(request,response);
            }else {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                //response.getWriter().write(JsonUtils.toJson(ApiResponse.generateErrorResponse(HttpStatus.UNAUTHORIZED, reason)));
                response.getWriter().write("ni ho payega");

                response.getWriter().write("sddddd");
            }
            /*Base64.decode()
            String pair=new String(Base64.decodeBase64(header.substring(6)));






            String userName=pair.split(":")[0];
            String password=pair.split(":")[1];
*/


            /*String token = new String(Base64.decode(base64Token));

            String username = "";
            String password = "";
            int delim = token.indexOf(":");

            if (delim != -1)
            {
                username = token.substring(0, delim);
                password = token.substring(delim + 1);
            }*/

        }
    }

}