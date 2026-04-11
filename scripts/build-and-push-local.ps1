param(
    [Parameter(Mandatory = $false)]
    [ValidateSet("demo-service", "test-app")]
    [string]$Module = "demo-service",

    [Parameter(Mandatory = $false)]
    [string]$Registry = "192.168.100.1:5000",

    [Parameter(Mandatory = $false)]
    [string]$Tag = "latest"
)

$ErrorActionPreference = "Stop"

# 1. Setup paths and names
$imageName = $Module
$localImage = "${imageName}:${Tag}"
$remoteImage = "${Registry}/${imageName}:${Tag}"

# We use the Dockerfile inside the module folder
$dockerfilePath = "${Module}/Dockerfile"

Write-Host "==> Building image for $Module using $dockerfilePath"
Write-Host "    Target: $remoteImage"

# 2. Build and Tag
# We set the build context to the module directory so 'COPY target/...' works correctly
docker build `
    -t $localImage `
    -t $remoteImage `
    -f $dockerfilePath $Module

if ($LASTEXITCODE -ne 0) {
    Write-Error "Docker build failed for $Module. Skipping push."
    exit $LASTEXITCODE
}

# 3. Push to Registry
Write-Host "==> Pushing to registry..."
docker push $remoteImage

if ($LASTEXITCODE -eq 0) {
    Write-Host "`nSuccessfully built and pushed $remoteImage"
}