## Deployment Notes

### Deploying through maven to heroku

```bash
# commit all changes
git push
git push heroku main
mvn clean heroku:deploy-war
```