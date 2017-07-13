vcl 4.0;

backend default {
    .host = "web";
    .port = "8080";
}

sub vcl_backend_response {
  set beresp.http.url = bereq.url;
  set beresp.ttl = 30d;
}

sub vcl_deliver {
  unset resp.http.url; # Optional
  if (obj.hits > 0) {
       set resp.http.X-Cache = "HIT";
    } else {
         set resp.http.X-Cache = "MISS";
   }
}

sub vcl_recv {
  if (req.http.x-ban == "true") {
    ban("obj.http.url ~ " + req.url); # Assumes req.url is a regex. This might be a bit too simple
    return(synth(200, "Ban added"));
   }

  if (req.url ~ "^/admin") {
        return (pass);
     } else {
        unset req.http.Cookie;
     }
 }


sub vcl_fetch {
      if (beresp.http.content-type ~ "text") {
              set beresp.do_gzip = true;
      }
}